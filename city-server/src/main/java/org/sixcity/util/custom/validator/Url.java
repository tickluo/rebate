package org.sixcity.util.custom.validator;

import org.sixcity.util.custom.validator.validator.UrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UrlValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {

    String message() default "{Url}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
