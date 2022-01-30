package com.unitbv.backend.temporary;

import com.unitbv.backend.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class TemporaryUsers {

    private HashMap<Integer, UserDTO> users = new HashMap<>();

    public void addUser(int confirmationCode, UserDTO user) {
        users.put(confirmationCode, user);
    }

    public Optional<UserDTO> getUser(int confirmationCode) {
        return Optional.of(users.get(confirmationCode));
    }

    public void removeUser(int confirmationCode) {
        users.remove(confirmationCode);
    }
}
