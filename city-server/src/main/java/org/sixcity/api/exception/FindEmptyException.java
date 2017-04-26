package org.sixcity.api.exception;

import exception.ServiceException;

public class FindEmptyException extends ServiceException {

    public static final String MESSAGE = "查询为空！";

    public FindEmptyException() {
        super(MESSAGE);
    }

    public FindEmptyException(String message) {
        super(message);
    }
}
