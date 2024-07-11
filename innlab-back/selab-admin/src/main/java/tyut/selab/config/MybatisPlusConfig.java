package tyut.selab.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisPlusConfig
 * @Description:初始化配置MybatisPlus参数
 * @Author: gmslymhn
 * @CreateTime: 2024-02-17 17:58
 * @Version: 1.0
 **/
@Configuration
@MapperScan(basePackages = {"tyut.selab.framework.mapper","tyut.selab.modular.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}

