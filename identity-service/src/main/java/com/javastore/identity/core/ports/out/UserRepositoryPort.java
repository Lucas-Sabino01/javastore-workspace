package com.javastore.identity.core.ports.out;

import com.javastore.identity.core.domain.User;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
}