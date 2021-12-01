package com.unitbv.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private int id;
    @NotNull(message = "The name of the restaurant is null!")
    @NotEmpty(message = "The name of the restaurant is empty!")
    private String name;
    @NotNull(message = "The address of the restaurant is null!")
    private AddressDTO address;
    @NotNull(message = "The manager of the restaurant is null!")
    private UserDTO manager;
    private List<UserDTO> sellers;
    private List<ProductDTO> products;
    private List<ScheduleDayDTO> workingProgram;
    private List<OrderDTO> orders;
}
