package com.javastore.identity.config;

import com.javastore.identity.adapters.out.persistence.UserRepositoryAdapter;
import com.javastore.identity.adapters.out.security.BCryptPasswordEncoderAdapter;
import com.javastore.identity.adapters.out.security.JwtTokenAdapter;
import com.javastore.identity.core.services.LoginUserService;
import com.javastore.identity.core.services.RegisterUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public RegisterUserService registerUserService(
            UserRepositoryAdapter userRepositoryAdapter,
            BCryptPasswordEncoderAdapter passwordEncoderAdapter) {
        return new RegisterUserService(userRepositoryAdapter, passwordEncoderAdapter);
    }

    @Bean
    public LoginUserService loginUserService(
            UserRepositoryAdapter userRepositoryAdapter,
            BCryptPasswordEncoderAdapter passwordEncoderAdapter,
            JwtTokenAdapter jwtTokenAdapter) {
        return new LoginUserService(userRepositoryAdapter, passwordEncoderAdapter, jwtTokenAdapter);
    }
}