package tyut.selab.framework.web;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.common.annotation.AccessLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.StringUtils;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.domain.entity.AccessLogEntity;
import tyut.selab.framework.mapper.AccessLogMapper;

import java.lang.reflect.Method;

/**
 * @ClassName: AccessLogAspect
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-15 20:26
 * @Version: 1.0
 **/
@Aspect
@Component
@Slf4j
public class AccessLogAspect {
    @Autowired
    private AccessLogMapper accessLogMapper;
    @Pointcut("@annotation(tyut.selab.common.annotation.AccessLogAnnotation)")
    public void accessLogPoinCut() {
    }

    @AfterReturning(returning = "result", value = " accessLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, R result) throws Throwable {
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = ServletUtils.getRequest();
        try {
            AccessLogEntity accessLogEntity = new AccessLogEntity();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            AccessLogAnnotation annotation = method.getAnnotation(AccessLogAnnotation.class);
            Object[] args = joinPoint.getArgs();
            String method1 = JSON.toJSONString(args[0]);
            if (annotation != null&& StringUtils.isNotEmpty(annotation.method())) {
                accessLogEntity.setMethod(annotation.method());
            }else {
                accessLogEntity.setMethod(method1);
            }
            accessLogEntity.setAccessIp(IpUtil.getIpAddress(request));
            accessLogEntity.setAccessUrl(request.getRequestURI());
            accessLogMapper.insert(accessLogEntity);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("日志记录异常!");
        }

    }

}
