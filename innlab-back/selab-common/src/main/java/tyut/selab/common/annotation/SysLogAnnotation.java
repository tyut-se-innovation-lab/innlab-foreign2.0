package tyut.selab.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: SysLogAnnotation
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 15:32
 * @Version: 1.0
 **/
@Target(ElementType.METHOD)//注解放置的目标位置即方法级别
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
@Documented
public @interface SysLogAnnotation {
    String operModul() default ""; // 操作模块

    String operType() default "";  // 操作类型

    String operDesc() default "";  // 操作说明
}