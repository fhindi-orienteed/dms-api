package com.dms.base.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException (String message){
        super(message);
    }
}
