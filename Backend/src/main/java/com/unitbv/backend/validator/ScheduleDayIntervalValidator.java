package com.unitbv.backend.validator;

import com.unitbv.backend.model.dto.ScheduleDayDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScheduleDayIntervalValidator implements ConstraintValidator<ScheduleDayCorrectInterval, ScheduleDayDTO> {
    @Override
    public boolean isValid(ScheduleDayDTO scheduleDay, ConstraintValidatorContext constraintValidatorContext) {
        return scheduleDay.getStartingHour().isBefore(scheduleDay.getFinishingHour());
    }
}
