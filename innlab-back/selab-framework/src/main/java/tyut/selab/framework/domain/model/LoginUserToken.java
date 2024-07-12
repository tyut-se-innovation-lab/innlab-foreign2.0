package tyut.selab.framework.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LoginUserToken
 * @Description:用户token类
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 21:17
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserToken {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    public LoginUserToken(LoginUser loginUser) {
        this.userId = loginUser.getUserId();
        this.userAccount = loginUser.getUserAccount();
        this.token = loginUser.getToken();
    }
}
