package com.unitbv.backend.model.entity;

import com.unitbv.backend.model.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity(name = "schedule_day")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDayDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "starting_hour", nullable = false)
    private LocalTime startingHour;
    @Column(name = "finishing_hour", nullable = false)
    private LocalTime finishingHour;
    @Column(name = "day", nullable = false)
    private Day day;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantDO restaurant;
}
