package tyut.selab.common.annotation;

/**
 * @ClassName: RequestSingleParam
 * @Description: 自定义注解接收单个参数
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 22:11
 * @Version: 1.0
 **/

import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.annotation.*;

/**
 * 自定义注解接收单个参数
 * @author created by wutong li on 2021/04/29
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestSingleParam {

    String value();

    boolean required() default true;

    String defaultValue() default ValueConstants.DEFAULT_NONE;
}
