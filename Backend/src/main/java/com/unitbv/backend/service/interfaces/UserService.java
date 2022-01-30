package com.unitbv.backend.service.interfaces;

import com.unitbv.backend.model.dto.AuthenticationRequest;
import com.unitbv.backend.model.dto.AuthenticationResponse;
import com.unitbv.backend.model.dto.UserDTO;

public interface UserService {

    void sendConfirmationCode(UserDTO user);

    UserDTO createUser(int confirmationCode);

    void deleteUser(int id);

    AuthenticationResponse logIn(AuthenticationRequest request);
}
