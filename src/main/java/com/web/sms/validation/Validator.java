package com.web.sms.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class Validator  implements ConstraintValidator<FlightMfdBy,String>
{
    List<String> approvedMfd = Arrays.asList("BOING","AIRBUS","CONCORD");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) 
    {
        if(value == null || value.isBlank())
            return false;
        
        if(approvedMfd.contains(value))
            return true;

        return false;
    }
    
}
