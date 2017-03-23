package validator.annotation;

import validator.Validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateParam {

    /**
     * 验证器
     */
    Validator[] validators() default {};

    /**
     * 参数的描述名称
     */
    String name() default "";
}
