package com.rednap.finalproject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class JwtUtils {
    @Value("${sensors.jwt.secret}")
    private String secret;
    @Value("${sensors.jwt.expiration-time}")
    private int expirationTime;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm).build();
    }

    public String generate(final String login) {
        return JWT.create()
                .withSubject(login)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(expirationTime))
                .sign(algorithm);
    }

    public Optional<DecodedJWT> validateAndDecode(final String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

}