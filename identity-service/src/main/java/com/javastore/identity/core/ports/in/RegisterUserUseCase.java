package com.javastore.identity.core.ports.in;

import com.javastore.identity.core.domain.User;

public interface RegisterUserUseCase {
    User register(User user);
}