package tyut.selab.framework.web.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.constant.UserConstants;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.MailUtil;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.RandomUtils;
import tyut.selab.common.utils.RedisUtils;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.common.utils.http.ServletUtils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LoginService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-20 14:48
 * @Version: 1.0
 **/
@Component
@Slf4j
public class LoginService {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${user.password.maxRetryCount}")
    private Integer maxNumberOfFailures;

    @Value("${user.password.lockTime}")
    private Integer lockTime;
    @Autowired
    private MailUtil mailUtil;

    public R mayLogin(String userAccount){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        Integer loginFailFrequency = (Integer) redisUtils.getCacheObject(getLoginFailKey(ip,userAccount));
        if (ObjectUtils.isNotNull(loginFailFrequency)&&loginFailFrequency>=maxNumberOfFailures){
            return R.error("登陆失败次数过多，请"+lockTime+"分钟后再试！");
        }
        return R.success();
    }
    public R mayRegister(String userAccount,String  userEmail,String registerCode){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        String registerCode1 = (String) redisUtils.getCacheObject(getRegisterKey(ip,userAccount,userEmail));
        if (ObjectUtils.isNotNull(registerCode1)&&registerCode.equals(registerCode1)){
            return R.success();
        }
        return R.error("验证码错误！");
    }
    public R mayRegisterVerify(){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        Integer registerFrequency = (Integer) redisUtils.getCacheObject(getRegisterFrequencyKey(ip));
        if (ObjectUtils.isNotNull(registerFrequency)&&registerFrequency>4){
            return R.error("发送邮件次数过多，ip锁定10分钟！");
        }
        return R.success();
    }

    public R addRegisterVerify(String userAccount,String  userEmail){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        String registerCode = RandomUtils.createCode1(UserConstants.VERIFCATION_CODE_LENGTH);
        if (redisUtils.getExpirationTime(getRegisterKey(ip,userAccount,userEmail))>250){
            return R.error("验证频率太高,请稍候再试！");
        }
        redisUtils.setCacheObject(getRegisterKey(ip,userAccount,userEmail), registerCode,5, TimeUnit.MINUTES);
        Integer registerFrequency = (Integer) redisUtils.getCacheObject(getRegisterFrequencyKey(ip));
        if (ObjectUtils.isNull(registerFrequency)){
            registerFrequency = 0;
        }
        registerFrequency++;
        redisUtils.setCacheObject(getRegisterFrequencyKey(ip),registerFrequency,10,TimeUnit.MINUTES);
        mailUtil.sendSampleMail(userEmail, "创新实验室注册验证码", registerCode);
        System.out.println("******执行发送验证码邮件成功******");
        return R.success("发送成功！");
    }

    private String getRegisterKey(String ip,String userAccount,String  userEmail){
        return KeyConstants.REGISTER_VERIFCATION_CODE+ip+"_"+userAccount+"_"+userEmail;
    }
    private String getRegisterFrequencyKey(String ip){
        return KeyConstants.REGISTER_VERIFCATION_FREQUENCY+ip;
    }

    private String getUserLoginIp(String ip){
        return KeyConstants.LOGIN_IP_KEY+ip;
    }

    private String getLoginFailKey(String ip,String userAccount){
        return KeyConstants.LOGIN_FAIL_FREQUENCY_KEY+ip+"_"+userAccount;
    }

    public R addLoginFailFrequency(String userAccount){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        Integer loginFailFrequency = (Integer) redisUtils.getCacheObject(getLoginFailKey(ip,userAccount));
        if (ObjectUtils.isNull(loginFailFrequency)){
            loginFailFrequency = 1;
        }else {
            loginFailFrequency++;
        }
        log.info(userAccount+"登陆失败次数:"+loginFailFrequency);
        redisUtils.setCacheObject(getLoginFailKey(ip,userAccount), loginFailFrequency,lockTime, TimeUnit.MINUTES);
        return R.error("账号或密码错误,剩余登陆次数："+(maxNumberOfFailures-loginFailFrequency)+"次！");
    }


}
