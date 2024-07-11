package tyut.selab.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: SwaggerConfig
 * @Description:整合knife4j
 * knife4j是一个集成Swagger的增强解决方案 核心为文档说明和在线调试功能
 * 免去了开发过程中写接口文档的痛苦
 * @Author: gmslymhn
 * @CreateTime: 2024-02-17 20:25
 * @Version: 1.0
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("创新实验室对外网站")
                        .description("创新实验室对外网站API文档")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}