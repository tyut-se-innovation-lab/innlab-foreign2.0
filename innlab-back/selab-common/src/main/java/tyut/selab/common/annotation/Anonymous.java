package tyut.selab.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Anonymous
 * @Description: 不鉴权注解
 * @Author: gmslymhn
 * @CreateTime: 2024-02-29 10:13
 * @Version: 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
}
