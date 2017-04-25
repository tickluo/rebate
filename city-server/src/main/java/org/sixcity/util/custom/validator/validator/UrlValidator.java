package org.sixcity.util.custom.validator.validator;

import org.sixcity.util.custom.validator.Url;
import util.ValidateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<Url, String> {

    @Override
    public void initialize(Url String) {
    }

    @Override
    public boolean isValid(String urlField, ConstraintValidatorContext cxt) {
        return urlField != null && ValidateUtils.isUrl(urlField);
    }

}
