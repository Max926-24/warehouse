package de.ait.warehouse.service;

import de.ait.warehouse.domain.User;
import de.ait.warehouse.domain.enums.Role;
import de.ait.warehouse.dto.user.UserRegistrationDto;
import de.ait.warehouse.exceptions.types.RegistrationException;
import de.ait.warehouse.repository.UserRepository;
import de.ait.warehouse.service.interfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register (UserRegistrationDto registrationDto) {
        if (repository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + registrationDto.getEmail() + " already exists");
        }

        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setName(registrationDto.getName());

        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(Role.ROLE_USER);

        repository.save(user);

    }
}

