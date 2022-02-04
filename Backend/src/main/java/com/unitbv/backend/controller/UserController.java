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

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestHeader(value = "jwt") String header) {
        if (permissionValidator.isAllowed(header, List.of(UserRole.ADMIN))) {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> editUser(@RequestHeader(value = "jwt") String header, @Valid @RequestBody UserDTO user) {
        if (permissionValidator.isAllowed(header, List.of(UserRole.values()))) {
            return new ResponseEntity<>(userService.editUser(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteUser(@RequestParam int id, @RequestHeader(value = "jwt") String header) {
        if (permissionValidator.isAllowed(header, List.of(UserRole.values()))) {
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
