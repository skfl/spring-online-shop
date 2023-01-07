package ru.skfl.skflshop.services.interfaces;

import org.mapstruct.control.MappingControl;
import ru.skfl.skflshop.dto.UserDTO;
import ru.skfl.skflshop.entities.User;

import java.util.Optional;

public interface UserSignUpService {
    Optional<User> signUp(User userToAdd);
}
