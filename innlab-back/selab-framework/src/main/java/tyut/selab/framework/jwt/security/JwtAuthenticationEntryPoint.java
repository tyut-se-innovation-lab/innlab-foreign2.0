package tyut.selab.framework.jwt.security;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tyut.selab.common.domain.R;
import tyut.selab.common.domain.ResponseCode;
import tyut.selab.common.utils.http.ServletUtils;

import java.io.IOException;

/**
 * @ClassName: JwtAuthenticationEntryPoint
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:47
 * @Version: 1.0
 **/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.setContentType("application/json;charset=UTF-8");

        System.out.println(request.getServletPath());
        String msg = "请先登录！";

        ServletUtils.renderString(response, JSONUtil.toJsonStr(R.error(ResponseCode.Unauthorized,msg)));
    }
}
