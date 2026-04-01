package com.javastore.identity.adapters.in.web;

import com.javastore.identity.adapters.in.web.dto.LoginRequest;
import com.javastore.identity.core.domain.User;
import com.javastore.identity.core.ports.in.LoginUserUseCase;
import com.javastore.identity.core.ports.in.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User userRequest) {
        User registeredUser = registerUserUseCase.register(userRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = loginUserUseCase.login(loginRequest.email(), loginRequest.password());
        return ResponseEntity.ok(token);
    }
}