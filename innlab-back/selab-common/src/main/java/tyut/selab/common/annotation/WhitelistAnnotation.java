package tyut.selab.common.annotation;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * 白名单注解，用于标记不需要认证的接口
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WhitelistAnnotation {
    /**
     * 是否启用白名单
     */
    boolean enabled() default true;

    /**
     * 请求方法，默认为所有方法
     */
    RequestMethod[] method() default {};


}

