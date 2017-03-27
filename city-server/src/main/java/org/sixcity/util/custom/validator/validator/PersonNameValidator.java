package org.sixcity.util.custom.validator.validator;

import org.sixcity.util.custom.validator.PersonName;
import util.ValidateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonNameValidator implements ConstraintValidator<PersonName, String> {

    @Override
    public void initialize(PersonName String) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        if (nameField == null) {
            return false;
        }
        return ValidateUtils.isPersonName(nameField);
    }
}

