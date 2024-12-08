package com.restFullApi.restFullApi.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest webRequest){
        ErrorDetail errorDetail=new ErrorDetail(LocalDateTime.now().now(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleUserNotFoundException(Exception ex,WebRequest request){
        ErrorDetail errorDetail=new ErrorDetail(LocalDateTime.now().now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetail>(errorDetail,HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode httpStatusCode,WebRequest request){
        ErrorDetail errorDetail=new ErrorDetail(LocalDateTime.now().now(),
                 "Total error "+ex.getErrorCount()+" First Error is"+ ex.getFieldError().getDefaultMessage()
                , request.getDescription(false));
        return new ResponseEntity(errorDetail,HttpStatus.BAD_REQUEST);
    }
}
