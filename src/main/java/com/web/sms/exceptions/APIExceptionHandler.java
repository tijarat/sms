package com.web.sms.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages="com.web.sms")
public class APIExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
        {
        System.out.println("handleMethodArgumentNotValid.....");
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(string -> string.getDefaultMessage()).collect(Collectors.toList());
        HttpServletRequest req = ((ServletWebRequest) request).getRequest();
        APIErrorResponse apiError = APIErrorResponseBuilder.getInstance()
                .withErrorId("SMS - "+LocalDateTime.now(ZoneOffset.UTC))
                .forPath(req.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(status.value())
                .build();
        return new ResponseEntity<>(apiError,headers, status);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public APIErrorResponse handleResponseStatusException(
        ResponseStatusException ex, HttpServletRequest request)
    {
            System.out.println("handleResponseStatusException.....");
            List<String> errors = Arrays.asList(ex.getReason());
        APIErrorResponse apiError = APIErrorResponseBuilder.getInstance()
                .withErrorId("SMS Not Found - "+LocalDateTime.now(ZoneOffset.UTC))
                .forPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.getStatusCode().value())
                .build();  
        return apiError;
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIErrorResponse> handleConstraintViolationException(
        ConstraintViolationException ex, HttpServletRequest request)
    {
            System.out.println("handleConstraintViolationException.....");
            List<String> errors = new ArrayList<>();
            for(ConstraintViolation<?> violation : ex.getConstraintViolations())
            {
                errors.add(violation.getMessage());
            }
        APIErrorResponse apiError = APIErrorResponseBuilder.getInstance()
                .withErrorId("SMS Voilation - "+LocalDateTime.now(ZoneOffset.UTC))
                .forPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .build(); 
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleException(Exception ex, HttpServletRequest req)
    {
        System.out.println("handleException.....");
        List<String> errors = Arrays.asList(ex.getLocalizedMessage());
        APIErrorResponse apiError = APIErrorResponseBuilder.getInstance()
                .withErrorId("SMS handleException - "+LocalDateTime.now(ZoneOffset.UTC))
                .forPath(req.getRequestURI())
                .withErrors(errors)
                .withMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build(); 
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
     }
    
}
