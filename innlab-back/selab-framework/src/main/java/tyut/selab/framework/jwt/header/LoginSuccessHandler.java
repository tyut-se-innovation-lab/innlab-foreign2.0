package tyut.selab.framework.jwt.header;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.ServletUtils;

import java.io.IOException;

/**
 * @ClassName: LoginSuccessHandler
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:52
 * @Version: 1.0
 **/
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        String msg = "登陆成功！";
        ServletUtils.renderString(response, JSON.toJSONString(R.success(msg)));
    }
}
