package com.waben.stock.interfaces.exception;

import com.waben.stock.interfaces.pojo.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc 第三种
 */
//@ControllerAdvice
public class ExceptionHandlerResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex, WebRequest request) throws
            IOException {
        return getResponseEntity(ex, request, "205",HttpStatus.NO_CONTENT);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
//        return super.handleNoHandlerFoundException(ex, headers, status, request);
        return getResponseEntity(ex, request, "404",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> serviceException(RuntimeException ex, WebRequest request) throws
            IOException {
        ServiceException exception = (ServiceException) ex;
        return getResponseEntity(ex, request, exception.getType(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex, WebRequest request, String code,HttpStatus status) {
        Response<Void> response = new Response();
        response.setCode(code);
        response.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), status, request);
    }
}
