package tyut.selab.common.constant;

/**
 * @ClassName: KeyConstants
 * @Description: Redis缓存的Key 常量
 * @Author: gmslymhn
 * @CreateTime: 2024-03-02 19:39
 * @Version: 1.0
 **/
public class KeyConstants {
    /**
     * 登录用户 redis token key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";
    /**
     * 登录用户 redis userId key
     */
    public static final String LOGIN_USER_KEY = "login_user:";
    /**
     * 验证频率 verify frequency key
     */
    public static final String VERIFY_FREQUENCY_KEY = "verify_frequency:";
    /**
     * 登陆失败频率 login fail key
     */
    public static final String LOGIN_FAIL_FREQUENCY_KEY = "login_fail_frequency:";
    /**
     * 登陆IP login ip key
     */
    public static final String LOGIN_IP_KEY = "login_ip:";
    /**
     * 验证频率 reset password key
     */
    public static final String RESET_PASSWORD_KEY = "reset_password:";

}
