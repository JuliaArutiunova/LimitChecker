package com.example.limit_checker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Size(min = 10, max = 10, message = "Invalid format: Expected 10 symbols")
@Pattern(regexp = "^\\d+$", message = "Invalid format: Only digits expected")
public @interface AccountId {
    String message() default "The string does not meet the requirements (10 digits long)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
