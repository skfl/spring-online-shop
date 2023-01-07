package ru.skfl.skflshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skfl.skflshop.dto.UserDTO;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserRole;
import ru.skfl.skflshop.entities.Weapon;
import ru.skfl.skflshop.mappers.UserMapper;
import ru.skfl.skflshop.mappers.WeaponMapper;
import ru.skfl.skflshop.services.interfaces.UserSignUpService;

import java.awt.*;
import java.util.Optional;

@Controller
public class SignUpController {

    @Autowired
    private UserSignUpService userSignUpService;

    @GetMapping("/signUp")
    public String getRegisterPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("message", "");
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpUser(@ModelAttribute UserDTO userFormData, Model model) {
        System.err.println("Received - " + userFormData);
        User newUser = UserMapper.INSTANCE.toEntity(userFormData);
        System.err.println("After Mapping - " + newUser);
        Optional<User> userToAdd = userSignUpService.signUp(newUser);
        if (userToAdd.isEmpty()) {
            model.addAttribute("message", "error");
            return "signUp";
        }
        return "redirect:/signIn";
    }

}
