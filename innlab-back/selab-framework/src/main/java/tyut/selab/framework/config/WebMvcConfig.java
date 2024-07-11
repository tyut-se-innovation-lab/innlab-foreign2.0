package tyut.selab.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tyut.selab.common.handler.RequestStringParamHandlerMethodArgumentResolver;

import java.util.List;

/**
 * @ClassName: WebMvcConfigurer
 * @Description:配置WebMvcConfigurer
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 22:16
 * @Version: 1.0
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册自定义的参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(new RequestStringParamHandlerMethodArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }

}
