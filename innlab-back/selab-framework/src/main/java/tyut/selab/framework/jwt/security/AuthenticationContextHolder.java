package tyut.selab.framework.jwt.security;

import org.springframework.security.core.Authentication;

/**
 * @ClassName: AuthenticationContextHolder
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 19:23
 * @Version: 1.0
 **/
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
