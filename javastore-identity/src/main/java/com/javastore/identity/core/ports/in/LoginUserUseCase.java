package com.javastore.identity.core.ports.in;

public interface LoginUserUseCase {
    String login(String email, String password);
}