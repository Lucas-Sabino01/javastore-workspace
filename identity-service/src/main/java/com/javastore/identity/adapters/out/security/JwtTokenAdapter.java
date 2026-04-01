package com.javastore.identity.adapters.out.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.javastore.identity.core.domain.User;
import com.javastore.identity.core.ports.out.TokenGeneratorPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtTokenAdapter implements TokenGeneratorPort {

    @Value("${api.security.token.secret:minha-chave-super-secreta-javastore-2026}")
    private String secret;

    @Override
    public String generate(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("javastore-identity")
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(algorithm);
    }
}