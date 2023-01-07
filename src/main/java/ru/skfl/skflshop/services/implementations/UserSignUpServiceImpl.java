package ru.skfl.skflshop.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserRole;
import ru.skfl.skflshop.entities.UserState;
import ru.skfl.skflshop.repositories.UserRepository;
import ru.skfl.skflshop.services.interfaces.UserSignUpService;

import java.util.Optional;
import java.util.Set;

@Service
public class UserSignUpServiceImpl implements UserSignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> signUp(User user) {

        if (userRepository.findByEmail(user.getUsername()).isPresent()) {
            System.err.println("Username already used");             //todo
            return Optional.empty();
        }
        User userToAdd = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .passwordHash(passwordEncoder.encode(user.getPasswordHash()))
                .roles(Set.of(UserRole.USER))
                .states(Set.of(UserState.NOT_CONFIRMED))
                .build();

        userRepository.save(userToAdd);
        return Optional.of(userToAdd);
    }
}
