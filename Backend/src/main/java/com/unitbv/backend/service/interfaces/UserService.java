package com.unitbv.backend.service.interfaces;

import com.unitbv.backend.model.dto.AuthenticationRequest;
import com.unitbv.backend.model.dto.AuthenticationResponse;
import com.unitbv.backend.model.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO user);

    void deleteUser(int id);

    AuthenticationResponse logIn(AuthenticationRequest request);
}
