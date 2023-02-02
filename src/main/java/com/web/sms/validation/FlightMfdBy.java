package com.web.sms.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=Validator.class)
public @interface FlightMfdBy 
{
    public String message() default "Invalid Manufactrer";
    public Class<?>[] groups() default{};
    public Class<? extends Payload>[] payload() default{};
}
