package tyut.selab.framework.web;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.common.annotation.LoginLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.AddressUtil;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.domain.dto.LoginDto;
import tyut.selab.framework.domain.entity.LoginLogEntity;
import tyut.selab.framework.domain.model.LoginUserToken;
import tyut.selab.framework.mapper.LoginLogMapper;
import tyut.selab.framework.web.service.TokenService;

import java.lang.reflect.Method;

/**
 * @ClassName: LoginLogAspect
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 20:17
 * @Version: 1.0
 **/
@Aspect
@Component
@Slf4j
public class LoginLogAspect {
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private TokenService tokenService;
    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(tyut.selab.common.annotation.LoginLogAnnotation)")
    public void loginLogPoinCut() {
    }
        /**
         * 记录操作日志
         * @param joinPoint 方法的执行点
         * @param result  方法返回值
         * @throws Throwable
         */
        @AfterReturning(returning = "result", value = "loginLogPoinCut()")
        public void saveOperLog(JoinPoint joinPoint, R result) throws Throwable {
            // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = ServletUtils.getRequest();
            try {
                LoginLogEntity loginLogEntity = new LoginLogEntity();
                // 从切面织入点处通过反射机制获取织入点处的方法
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                //获取切入点所在的方法
                Method method = signature.getMethod();
                //获取操作
                LoginLogAnnotation annotation = method.getAnnotation(LoginLogAnnotation.class);
                Object[] args = joinPoint.getArgs();
                    LoginDto loginDto = (LoginDto) args[0];
                if (SecurityUtils.isLogin()) {
                    LoginUserToken loginUserToken = tokenService.getLoginUserToken(request);
                    loginLogEntity.setLoginIp(loginUserToken.getIpaddr());
                    loginLogEntity.setBrowser(loginUserToken.getBrowser());
                    loginLogEntity.setLoginLocation(loginUserToken.getLoginLocation());
                    loginLogEntity.setOsName(loginUserToken.getOs());
                    loginLogEntity.setUserAccount(loginDto.getAccount());
                    loginLogEntity.setResultCode(result.getCode());
                    loginLogEntity.setResultMessage(result.getMessage());
                }else {
                    UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
                    //获取IP地址
                    String ip = IpUtil.getIpAddress(request);
                    //获取操作系统
                    String osName = userAgent.getOperatingSystem().getName();
                    //获取浏览器类型
                    String browser = userAgent.getBrowser().getName();
                    //获取登录地址
                    String location = AddressUtil.getAddressByIP(ip);
                    loginLogEntity.setLoginIp(ip);
                    loginLogEntity.setBrowser(browser);
                    loginLogEntity.setLoginLocation(location);
                    loginLogEntity.setOsName(osName);
                    loginLogEntity.setUserAccount(loginDto.getAccount());
                    loginLogEntity.setResultCode(result.getCode());
                    loginLogEntity.setResultMessage(result.getMessage());
                }
                loginLogMapper.insert(loginLogEntity);

            } catch (Exception e) {
                e.printStackTrace();
                log.error("日志记录异常!");
            }
        }
    }
