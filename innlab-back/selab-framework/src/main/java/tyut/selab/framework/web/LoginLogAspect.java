package tyut.selab.framework.web;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.AddressUtil;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.domain.dto.LoginDto;
import tyut.selab.framework.domain.entity.LoginLogEntity;
import tyut.selab.framework.domain.model.LoginUserToken;
import tyut.selab.framework.mapper.LoginLogMapper;
import tyut.selab.framework.web.service.TokenService;


/**
 * @ClassName: LoginLogAspect
 * @Description:使用@Around环绕通知
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 22:08
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

    @Pointcut("@annotation(tyut.selab.common.annotation.LoginLogAnnotation)")
    public void loginLogPointCut() {
    }

    /**
     * 使用环绕通知，确保异常情况下也能记录日志
     */
    @Around("loginLogPointCut()")
    public Object saveLoginLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ServletUtils.getRequest();
        Object result = null;
        Exception exception = null;

        // 在执行方法前可以记录一些信息
        String userAccount = extractUserAccount(joinPoint.getArgs());

        try {
            // 执行目标方法
            result = joinPoint.proceed();
        } catch (Exception e) {
            exception = e;
            throw e; // 重新抛出异常，让全局异常处理器处理
        } finally {
            // 无论成功还是异常，都记录日志
            recordLoginLog(request, joinPoint, result, exception, userAccount);
        }

        return result;
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(HttpServletRequest request, ProceedingJoinPoint joinPoint,
                                Object result, Exception exception, String userAccount) {
        if (request == null) {
            log.warn("无法获取HttpServletRequest，跳过登录日志记录");
            return;
        }

        try {
            LoginLogEntity loginLogEntity = new LoginLogEntity();

            // 设置基础信息
            loginLogEntity.setUserAccount(userAccount);
//            loginLogEntity.setLoginTime(LocalDateTime.now());

            // 获取请求信息
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            String ip = IpUtil.getIpAddress(request);
            String osName = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            String location = AddressUtil.getAddressByIP(ip);

            // 设置设备信息
            loginLogEntity.setLoginIp(ip);
            loginLogEntity.setBrowser(browser);
            loginLogEntity.setLoginLocation(location);
            loginLogEntity.setOsName(osName);

            // 根据执行结果设置状态
            if (exception != null) {
                // 异常情况
                loginLogEntity.setResultCode(500); // 或其他错误码
                loginLogEntity.setResultMessage(exception.getMessage());
            } else if (result instanceof R) {
                // 正常返回R对象
                R rResult = (R) result;
                loginLogEntity.setResultCode(rResult.getCode());
                loginLogEntity.setResultMessage(rResult.getMessage());

                // 如果登录成功，尝试获取更多用户信息
                if (rResult.getCode()==200) {
                    try {
                        LoginUserToken loginUserToken = tokenService.getLoginUserToken(request);
                        if (loginUserToken != null) {
                            loginLogEntity.setLoginIp(loginUserToken.getIpaddr());
                            loginLogEntity.setBrowser(loginUserToken.getBrowser());
                            loginLogEntity.setLoginLocation(loginUserToken.getLoginLocation());
                            loginLogEntity.setOsName(loginUserToken.getOs());
                        }
                    } catch (Exception e) {
                        log.debug("无法从token获取用户信息: {}", e.getMessage());
                    }
                }
            } else {
                // 其他返回类型
                loginLogEntity.setResultCode(200);
                loginLogEntity.setResultMessage("success");
            }

            // 保存日志
            loginLogMapper.insert(loginLogEntity);
            log.debug("登录日志记录完成: account={}, ip={}, resultCode={}",
                    userAccount, ip, loginLogEntity.getResultCode());

        } catch (Exception e) {
            log.error("登录日志记录异常: {}", e.getMessage(), e);
        }
    }

    /**
     * 从方法参数中提取用户账号
     */
    private String extractUserAccount(Object[] args) {
        if (args == null || args.length == 0) {
            return "unknown";
        }

        for (Object arg : args) {
            if (arg instanceof LoginDto) {
                return ((LoginDto) arg).getAccount();
            }
        }

        for (Object arg : args) {
            if (arg instanceof String) {
                return (String) arg;
            }
        }

        return "unknown";
    }
}
