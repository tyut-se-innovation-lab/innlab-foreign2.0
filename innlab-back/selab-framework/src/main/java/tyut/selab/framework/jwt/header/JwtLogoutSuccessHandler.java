package tyut.selab.framework.jwt.header;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.jwt.JwtUtil;

import java.io.IOException;

/**
 * @ClassName: JwtLogoutSuccessHandler
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:53
 * @Version: 1.0
 **/
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader(jwtUtil.gerHeader(), "");
        SecurityContextHolder.clearContext();

        String msg = "登出成功！";
        ServletUtils.renderString(response, JSON.toJSONString(R.success(msg)));
    }
}
