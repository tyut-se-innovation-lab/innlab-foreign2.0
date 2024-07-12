package tyut.selab.framework.jwt.header;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.ServletUtils;

import java.io.IOException;

/**
 * @ClassName: LoginFailureHandler
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:53
 * @Version: 1.0
 **/
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String msg = "登陆失败！";

        ServletUtils.renderString(response, JSON.toJSONString(R.error(msg)));
    }
}
