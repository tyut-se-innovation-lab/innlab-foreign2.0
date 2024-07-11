package tyut.selab.common.exception;

/**
 * @ClassName: ErrorCode
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-02-08 00:15
 * @Version: 1.0
 **/
public interface ErrorCode {
    /**
     * 内部服务器错误
     */
    int INTERNAL_SERVER_ERROR = 500;
    /**
     * 未经授权
     */
    int UNAUTHORIZED = 401;
    /**
     * 未经授权
     */
    int TOKEN_OVERDUE = 409;

    /**
     * 首次登陆
     */
    int FIRSTLOGIN = 1410;
    /**
     * 不能为null
     */
    int NOT_NULL = 10001;
    /**
     * 数据库记录不存在
     */
    int DB_RECORD_EXISTS = 10002;
    /**
     * 参数出错
     */
    int PARAMS_GET_ERROR = 10003;
    /**
     * 密码错误
     */
    int ACCOUNT_PASSWORD_ERROR = 10004;
    /**
     * 帐户禁用
     */
    int ACCOUNT_DISABLE = 10005;
    /**
     * 账号异常
     */
    int ACCOUNT_ABNORMAL = 10008;
    /**
     * 标识符不为空
     */
    int IDENTIFIER_NOT_NULL = 10006;
    /**
     * 验证码失败
     */
    int CAPTCHA_ERROR = 10007;

}
