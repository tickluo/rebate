package org.sixcity.api.exception;


import exception.AuthorityException;
import model.ResultCode;

public class TokenInvalidException extends AuthorityException {

    public static final String MESSAGE = "token非法！";

    public TokenInvalidException() {
        super(ResultCode.SSO_TOKEN_ERROR, MESSAGE);
    }

    public TokenInvalidException(String message) {
        super(ResultCode.SSO_TOKEN_ERROR, message);
    }

}
