package com.javastore.identity.core.services;

import com.javastore.identity.core.domain.User;
import com.javastore.identity.core.ports.in.LoginUserUseCase;
import com.javastore.identity.core.ports.out.PasswordEncoderPort;
import com.javastore.identity.core.ports.out.TokenGeneratorPort;
import com.javastore.identity.core.ports.out.UserRepositoryPort;

public class LoginUserService implements LoginUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public LoginUserService(UserRepositoryPort userRepositoryPort,
                            PasswordEncoderPort passwordEncoderPort,
                            TokenGeneratorPort tokenGeneratorPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
    }

    @Override
    public String login(String email, String password) {
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos."));

        if (!passwordEncoderPort.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("E-mail ou senha inválidos.");
        }

        return tokenGeneratorPort.generate(user);
    }
}