package com.unitbv.backend.service.interfaces;

import com.unitbv.backend.model.dto.AuthenticationRequest;
import com.unitbv.backend.model.dto.AuthenticationResponse;
import com.unitbv.backend.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO createUser(UserDTO user);

    UserDTO editUser(UserDTO user);

    void deleteUser(int id);

    AuthenticationResponse logIn(AuthenticationRequest request);
}
