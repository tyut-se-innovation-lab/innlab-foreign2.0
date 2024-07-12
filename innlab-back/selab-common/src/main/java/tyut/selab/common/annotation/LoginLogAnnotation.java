package tyut.selab.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: LoginLogAnnotation
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 20:33
 * @Version: 1.0
 **/
@Target(ElementType.METHOD)//注解放置的目标位置即方法级别
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
@Documented
public @interface LoginLogAnnotation {

}
