package tyut.selab.framework.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tyut.selab.common.exception.ErrorCode;
import tyut.selab.common.exception.ServiceException;
import tyut.selab.framework.domain.model.LoginUser;

/**
 * @ClassName: SecurityUtils
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 22:08
 * @Version: 1.0
 **/
public class SecurityUtils {
    /**
     * 用户ID
     **/
    public static Integer getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new ServiceException("获取用户ID异常", ErrorCode.ACCOUNT_ABNORMAL);
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUserAccount() {
        try {
            return getLoginUser().getUserAccount();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", ErrorCode.ACCOUNT_ABNORMAL);
        }
    }

    public static String getPermission(){
        try {
            return getLoginUser().getPermission();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", ErrorCode.ACCOUNT_ABNORMAL);
        }
    }
    public static String getUserDepartment(){
        try {
            return getLoginUser().getDepartment();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", ErrorCode.ACCOUNT_ABNORMAL);
        }
    }




    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            LoginUser loginUser = (LoginUser) getAuthentication().getPrincipal();
            //用户不正常
            if (!loginUser.isUserState()) {
                throw new ServiceException("用户被禁止", ErrorCode.ACCOUNT_DISABLE);
            }
            return loginUser;
        } catch (Exception e) {
            throw new ServiceException("获取用户信息异常", ErrorCode.ACCOUNT_DISABLE);
        }
    }

    /**
     * 是否登录，true登录
     *
     * @return
     */
    public static boolean isLogin() {
        Authentication auth = getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        password = password;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(rawPassword);
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("123456"));
    }
}
