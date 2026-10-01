package de.ait.warehouse.security.controller;

import de.ait.warehouse.dto.user.UserRegistrationDto;
import de.ait.warehouse.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRegistrationDto registrationDto) {
        userService.register(registrationDto);
    }
}
