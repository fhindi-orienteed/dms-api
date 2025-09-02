package com.dms.base.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("error","Bad Request");
        body.put("message",ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status",HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message",ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }
}
