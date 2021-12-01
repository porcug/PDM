package com.unitbv.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private int id;
    @NotNull(message = "The details of the address is null!")
    @NotEmpty(message = "The details of the address is empty!")
    private String details;
    private double latitude;
    private double longitude;
    private OrderDTO order;
    private RestaurantDTO restaurant;
}

