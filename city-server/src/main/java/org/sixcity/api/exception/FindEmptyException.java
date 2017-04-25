package org.sixcity.api.exception;

import exception.ServiceException;

public class FindEmptyException extends ServiceException {

    public FindEmptyException(String message) {
        super(message);
    }
}
