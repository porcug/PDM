package com.unitbv.backend.security;

import com.unitbv.backend.decoder.JWTDecoder;
import com.unitbv.backend.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionValidator {

    @Autowired
    private JWTDecoder jwtDecoder;

    public boolean isAllowed(String header, List<UserRole> roleList) {
        return roleList.contains(jwtDecoder.getUserRoleFromToken(header));
    }
}
