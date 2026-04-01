package com.javastore.identity.adapters.out.persistence;

import com.javastore.identity.core.domain.User;
import com.javastore.identity.core.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository springDataUserRepository;

    public UserRepositoryAdapter(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPassword());

        UserEntity savedEntity = springDataUserRepository.save(entity);

        return new User(savedEntity.getId(), savedEntity.getName(), savedEntity.getEmail(), savedEntity.getPassword());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword()));
    }
}