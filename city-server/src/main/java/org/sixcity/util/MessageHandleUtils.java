package org.sixcity.util;

import org.springframework.validation.FieldError;

import java.util.List;

public class MessageHandleUtils {

    public static String getControllerParamsInvalidMessage(List<FieldError> fieldErrors) {
        String errorMsg = "";
        for (FieldError errors : fieldErrors) {
            //errorMsg += errors.getField() + "<br>";
            //errorMsg += errors.getRejectedValue() + "<br>";
            errorMsg += errors.getDefaultMessage() + "<br>";
        }
        return errorMsg;
    }
}
