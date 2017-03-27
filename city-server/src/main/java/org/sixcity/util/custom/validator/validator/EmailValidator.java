package org.sixcity.util.custom.validator.validator;

import org.sixcity.util.custom.validator.Email;
import util.ValidateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email String) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        if (emailField == null) {
            return false;
        }
        return ValidateUtils.isEmail(emailField);
    }
}
