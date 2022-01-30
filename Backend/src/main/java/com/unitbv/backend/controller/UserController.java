package com.unitbv.backend.controller;

import com.unitbv.backend.model.dto.AuthenticationRequest;
import com.unitbv.backend.model.dto.AuthenticationResponse;
import com.unitbv.backend.model.dto.UserDTO;
import com.unitbv.backend.model.enums.UserRole;
import com.unitbv.backend.security.PermissionValidator;
import com.unitbv.backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionValidator permissionValidator;

    @PostMapping("/sendConfirmationCode")
    public ResponseEntity<Void> sendConfirmationCode(@Valid @RequestBody UserDTO user) {
        userService.sendConfirmationCode(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody int confirmationCode) {
        return new ResponseEntity<>(userService.createUser(confirmationCode), HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteUser(@RequestParam int id, @RequestHeader(value = "jwt") String header) {
        if (permissionValidator.isAllowed(header, List.of(UserRole.ADMIN, UserRole.CLIENT))) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(userService.logIn(request), HttpStatus.OK);
    }
}
