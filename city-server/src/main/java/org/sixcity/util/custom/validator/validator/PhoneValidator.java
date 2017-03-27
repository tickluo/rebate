package org.sixcity.util.custom.validator.validator;

import org.sixcity.util.custom.validator.Phone;
import util.ValidateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone String) {
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        if (phoneField == null) {
            return false;
        }
        return ValidateUtils.isMobile(phoneField);
    }
}
