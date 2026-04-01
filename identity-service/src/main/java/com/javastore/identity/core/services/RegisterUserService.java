package com.javastore.identity.core.services;

import com.javastore.identity.core.domain.User;
import com.javastore.identity.core.ports.in.RegisterUserUseCase;
import com.javastore.identity.core.ports.out.PasswordEncoderPort;
import com.javastore.identity.core.ports.out.UserRepositoryPort;

public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public RegisterUserService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public User register(User user) {
        if (userRepositoryPort.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Este e-mail já está em uso!");
        }

        String encodedPassword = passwordEncoderPort.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepositoryPort.save(user);
    }
}