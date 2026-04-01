package com.javastore.identity.core.ports.out;

import com.javastore.identity.core.domain.User;

public interface TokenGeneratorPort {
    String generate(User user);
}