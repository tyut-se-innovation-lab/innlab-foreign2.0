package tyut.selab.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Log
 * @Description: 标注在控制器类或方法上，判断是否进行日志记录
 * @Author: gmslymhn
 * @CreateTime: 2024-02-29 10:17
 * @Version: 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 是否进行日志记录，默认开启
     */
    boolean openLog() default true;
}
