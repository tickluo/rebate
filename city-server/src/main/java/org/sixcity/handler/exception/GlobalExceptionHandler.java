package org.sixcity.handler.exception;

import exception.DaoException;
import exception.ServiceException;
import model.Result;
import org.sixcity.api.exception.FindEmptyException;

import org.sixcity.api.exception.TokenInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result noHandlerFoundException(Exception exception) {
        return Result.createErrorResult(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @ExceptionHandler(value = FindEmptyException.class)
    public Result FindEmptyException(FindEmptyException exception) {
        return Result.createErrorResult(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result ServiceException(ServiceException exception) {
        return Result.createErrorResult(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = DaoException.class)
    public Result DaoException(DaoException exception) {
        return Result.createErrorResult(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = TokenInvalidException.class)
    public Result TokenInvalidException(TokenInvalidException exception) {
        return Result.createErrorResult(exception.getCode(), exception.getMessage());
    }

}
