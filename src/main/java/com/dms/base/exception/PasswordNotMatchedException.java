package com.dms.base.exception;

public class PasswordNotMatchedException extends RuntimeException{
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
