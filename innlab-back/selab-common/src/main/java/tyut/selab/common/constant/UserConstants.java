package tyut.selab.common.constant;

/**
 * @ClassName: UserConstants
 * @Description: 用户常量信息
 * @Author: gmslymhn
 * @CreateTime: 2024-03-02 19:53
 * @Version: 1.0
 **/
public class UserConstants {
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "TYUT_SELAB";
    /**
     * 正常状态
     */
    public static final String NORMAL = "1";
    /**
     * 异常状态
     */
    public static final String EXCEPTION = "0";
    /**
     * 账号长度限制
     */
    public static final int USERACCOUNT_MIN_LENGTH = 5;
    public static final int USERACCOUNT_MAX_LENGTH = 20;
    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 用户默认头像
     */
    public static final Integer AVATAR_ID = 1;

    /**
     * 活动/项目默认头像
     */
    public static final Integer HEAD_ID = 2;

    /**
     * 默认markdown
     */
    public static final Integer MARKDOWN_ID =3;
    /**
     * 验证码长度
     */
    public static final int VERIFCATION_CODE_LENGTH = 6;
}
