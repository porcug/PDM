package com.unitbv.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    @NotNull(message = "The name of the product is null!")
    @NotEmpty(message = "The name of the product is empty!")
    private String name;
    @Positive(message = "The price of the product must be greater than 0!")
    private double price;
    @NotNull(message = "The description of the product is null!")
    @NotEmpty(message = "The description of the product is empty!")
    private String description;
    @Positive(message = "The quantity of the product must be greater than 0!")
    private int quantity;
    @PositiveOrZero(message = "The quantity limit of the product must be greater or equal with 0!")
    private int quantityLimit;
    private boolean available;
    @NotNull(message = "The restaurant where the product is located is empty!")
    private RestaurantDTO restaurant;
    private List<OrderDTO> orders;
}
