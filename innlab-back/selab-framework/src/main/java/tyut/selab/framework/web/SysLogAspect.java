package tyut.selab.framework.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.common.annotation.SysLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.domain.entity.SysLogEntity;
import tyut.selab.framework.mapper.SysLogMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SysLogAspect
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 15:35
 * @Version: 1.0
 **/
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    @Autowired
    private SysLogMapper sysLogMapper;


    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(tyut.selab.common.annotation.SysLogAnnotation)")
    public void operLogPoinCut() {
    }

    /**
     * 记录操作日志
     * @param joinPoint 方法的执行点
     * @param result  方法返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "result", value = "operLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, R result) throws Throwable {
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = ServletUtils.getRequest();
        try {
            SysLogEntity sysLog = new SysLogEntity();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            SysLogAnnotation annotation = method.getAnnotation(SysLogAnnotation.class);
            if (annotation != null) {
                sysLog.setModel(annotation.operModul());
                sysLog.setType(annotation.operType());
                sysLog.setDescription(annotation.operDesc());
            }
//            System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
//            System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
//            System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
//            System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
//            //获取传入目标方法的参数
//            Object[] args = joinPoint.getArgs();
//            for (int i = 0; i < args.length; i++) {
//                System.out.println("第" + (i+1) + "个参数为:" + args[i]);
//            }
//            System.out.println("被代理的对象:" + joinPoint.getTarget());
//            System.out.println("代理对象自己:" + joinPoint.getThis());


            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            sysLog.setMethod(methodName); // 类名.请求方法

            //操作用户 --登录时有把用户的信息保存在session中，可以直接取出
            String user = SecurityUtils.getUserAccount();
            sysLog.setUserAccount(user);

            sysLog.setIp(IpUtil.getIpAddress(request)); //操作IP IPUtils工具类网上大把的，比如工具类集锦的hutool.jar
            sysLog.setUrl(request.getRequestURI()); // 请求URI

            String params = Arrays.toString(joinPoint.getArgs());
            if (params.length()<500){
                sysLog.setParams(params); // 请求参数
            }
            //需要先判断返回值是不是Map <String, Object>，如果不是會拋異常，需要控制层的接口返回数据格式统一
            //如果嫌返回格式统一太麻烦建议日志保存时去掉操作结果
            sysLog.setResultCode(result.getCode());
            sysLog.setResultMessage(result.getMessage()); //獲取方法返回值中的msg，如果上面的類型錯誤就拿不到msg就會拋異常

            //保存日志
            sysLogMapper.insert(sysLog);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("日志记录异常!");
        }
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, Object> converMap(Map<String, String[]> paramMap) {
        Map<String, Object> params = new HashMap<String, Object>();
        int length;
        //将Map<String, String[]>转为普通map
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            length = entry.getValue().length;
            if (length == 1) {
                params.put(entry.getKey(), entry.getValue()[0]);
            } else if (length > 1) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    /**
     * 获取json格式 请求参数
     */
    public String getJsonStrByRequest(HttpServletRequest request) {
        String param = null;
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            if (ObjectUtils.isNotNull(jsonObject)){
                param = jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }


    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

}
