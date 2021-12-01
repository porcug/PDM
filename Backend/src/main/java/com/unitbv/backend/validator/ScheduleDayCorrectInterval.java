package com.unitbv.backend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ScheduleDayIntervalValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduleDayCorrectInterval {

    public String message() default "The interval of the schedule day is not correct!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}