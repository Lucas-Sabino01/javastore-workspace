package com.javastore.gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Value("${api.security.token.secret:minha-chave-super-secreta-javastore-2026}")
    private String secret;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Token não encontrado", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Formato de token inválido", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.replace("Bearer ", "");

            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                DecodedJWT decodedJWT = JWT.require(algorithm)
                        .withIssuer("javastore-identity")
                        .build()
                        .verify(token);

                var mutatedRequest = exchange.getRequest().mutate()
                        .header("X-User-Email", decodedJWT.getSubject())
                        .build();

                var mutatedExchange = exchange.mutate().request(mutatedRequest).build();

                return chain.filter(mutatedExchange);

            } catch (Exception e) {
                return onError(exchange, "Token inválido ou expirado", HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    public static class Config {}
}