package org.sixcity.util.custom.validator;

import org.sixcity.util.custom.validator.validator.PersonNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = PersonNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonName {


    String message() default "{PersonName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}