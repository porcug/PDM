package com.unitbv.backend.validator;

import com.unitbv.backend.model.dto.OrderDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderTimeIntervalValidator implements ConstraintValidator<OrderCorrectTimeInterval, OrderDTO> {

    @Override
    public boolean isValid(OrderDTO order, ConstraintValidatorContext constraintValidatorContext) {
        return order.getCreatedDate().isBefore(order.getFinishedDate());
    }
}
