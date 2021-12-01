package com.unitbv.backend.model.dto;

import com.unitbv.backend.model.enums.Day;
import com.unitbv.backend.validator.ScheduleDayCorrectInterval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ScheduleDayCorrectInterval
public class ScheduleDayDTO {

    private int id;
    private LocalTime startingHour;
    private LocalTime finishingHour;
    @NotNull(message = "The day of the schedule day is null!")
    private Day day;
    @NotNull(message = "The restaurant which has this schedule day is null!")
    private RestaurantDTO restaurant;
}
