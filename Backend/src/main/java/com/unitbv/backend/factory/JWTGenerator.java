package com.unitbv.backend.factory;

import com.unitbv.backend.model.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class JWTGenerator {

    private MessageSource messageSource;

    @Autowired
    public JWTGenerator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String generate(UserDTO user) {
        return Jwts.builder()
                .setClaims(generateClaimsForUserJWT(user))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(messageSource.getMessage("api.jwt.secret.key", null, Locale.ENGLISH)))
                .compact();
    }

    private Claims generateClaimsForUserJWT(UserDTO user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());
        claims.put("role", user.getRole());
        return claims;
    }
}
