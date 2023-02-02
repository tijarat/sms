package com.web.sms.exceptions;

import java.util.List;

public class APIErrorResponseBuilder 
{
    private static APIErrorResponseBuilder builder;
    private APIErrorResponseBuilder()
    {
    
    }
    private String errorId;
    private int status;
    private String message;
    private List<String> errors;
    private String path;
    
    public synchronized static APIErrorResponseBuilder getInstance()
    {
        if(builder == null)
        {
            builder = new APIErrorResponseBuilder();
        }
        return builder;
    }
    
    public APIErrorResponseBuilder withErrorId(String errorId)
    {
        builder.errorId = errorId;
        return builder;
    }
    
    public APIErrorResponseBuilder withStatus(int status)
    {
        builder.status = status;
        return builder;
    }

    public APIErrorResponseBuilder withMessage(String message)
    {
        builder.message = message;
        return builder;
    }   
    
    public APIErrorResponseBuilder withErrors(List<String> errors)
    {
        builder.errors = errors;
        return builder;
    }    
    
    public APIErrorResponseBuilder forPath(String path)
    {
        builder.path = path;
        return builder;
    }      
    
    public APIErrorResponse build()
    {
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        apiErrorResponse.setErrorId(builder.errorId);
        apiErrorResponse.setErrors(builder.errors);
        apiErrorResponse.setMessage(builder.message);
        apiErrorResponse.setStatus(builder.status);
        apiErrorResponse.setPath(builder.path);
        return  apiErrorResponse;
    
    }
    
}
