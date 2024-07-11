package tyut.selab.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: RepeatSubmit
 * @Description: 防止表单重复提交
 * @Author: gmslymhn
 * @CreateTime: 2024-02-29 10:16
 * @Version: 1.0
 **/
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * 间隔时间(ms)，小于此时间视为重复提交
     */
    public int interval() default 5000;

    /**
     * 提示消息
     */
    public String message() default "不允许重复提交，请稍候再试";
}
