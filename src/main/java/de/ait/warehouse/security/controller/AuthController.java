package de.ait.warehouse.security.controller;

import de.ait.warehouse.dto.user.UserRegistrationDto;
import de.ait.warehouse.security.dto.LoginRequestDto;
import de.ait.warehouse.security.dto.TokenResponseDto;
import de.ait.warehouse.security.service.AuthService;
import de.ait.warehouse.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRegistrationDto registrationDto) {
        userService.register(registrationDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@Valid @RequestBody LoginRequestDto loginDto) {
        return authService.login(loginDto);
    }
}
