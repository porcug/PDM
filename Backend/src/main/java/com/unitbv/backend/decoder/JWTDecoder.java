package com.unitbv.backend.decoder;

import com.unitbv.backend.exception.ResourceNotFoundException;
import com.unitbv.backend.model.enums.UserRole;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Locale;

@Component
public class JWTDecoder {

    @Autowired
    private MessageSource messageSource;

    public UserRole getUserRoleFromToken(String token) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] tokenParts = token.split("\\.");
        String body = new String(decoder.decode(tokenParts[1]));
        JSONObject jsonObject = new JSONObject(body);
        try {
            return UserRole.valueOf(jsonObject.getString("role"));
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFoundException(messageSource.getMessage("api.error.role.not.found", null, Locale.ENGLISH));
        }
    }
}
