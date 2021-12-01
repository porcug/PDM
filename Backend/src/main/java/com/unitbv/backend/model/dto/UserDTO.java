package com.unitbv.backend.model.dto;

import com.unitbv.backend.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id;
    @NotNull(message = "The first name is null!")
    @NotEmpty(message = "The first name is empty!")
    private String firstName;
    @NotNull(message = "The last name is null!")
    @NotEmpty(message = "The last name is empty!")
    private String lastName;
    @NotNull(message = "The email is null!")
    @NotEmpty(message = "The email is empty!")
    @Pattern(regexp = "^[a-zA-Z0-9!#$&_*?^{}-~]+(\\.[a-zA-Z0-9!#$&_*?^{}-~]+)*@([a-z0-9]+" +
            "([a-zA-z0-9-]*)\\.)+[a-zA-Z]{2,3}$", message = "The email is invalid!")
    private String email;
    @NotNull(message = "The password is null!")
    @NotEmpty(message = "The password is empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct}).{10,}$", message = "The password is invalid!")
    private String password;
    @NotNull(message = "The phone is null!")
    @NotEmpty(message = "The phone is empty!")
    @Pattern(regexp = "^((\\+40)|(0040)|0)[0-9]{9}$", message = "The phone is invalid!")
    private String phone;
    @NotNull(message = "The role is null!")
    private UserRole role;
    private RestaurantDTO restaurantManager;
    private RestaurantDTO restaurantSeller;
    private List<OrderDTO> clientOrders;
    private List<OrderDTO> deliveryOrders;
}

