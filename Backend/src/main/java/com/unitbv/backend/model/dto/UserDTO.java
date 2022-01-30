package com.unitbv.backend.model.dto;

import com.unitbv.backend.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private int id;
    @NotNull(message = "The first name of the user is null!")
    @NotEmpty(message = "The first name of the user is empty!")
    private String firstName;
    @NotNull(message = "The last name of the user is null!")
    @NotEmpty(message = "The last name of the user is empty!")
    private String lastName;
    @NotNull(message = "The email of the user is null!")
    @Email(message = "The email is invalid!")
    private String email;
    @NotNull(message = "The password of the user is null!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct}).{10,}$", message = "The password must be at least 10 characters long, contain at least one uppercase letter, one lowercase letter, and one special character.")
    private String password;
    @NotNull(message = "The role of the user is null!")
    private UserRole role;
}
