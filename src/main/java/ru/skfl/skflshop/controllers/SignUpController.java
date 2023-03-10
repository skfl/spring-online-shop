package ru.skfl.skflshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skfl.skflshop.dto.UserDTO;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.mappers.UserMapper;
import ru.skfl.skflshop.services.interfaces.UserSignUpService;

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

    @PostMapping(value = "/signUp")
    public String signUpUser(@ModelAttribute UserDTO userFormData, Model model) {
        User newUser = UserMapper.INSTANCE.toEntity(userFormData);
        Optional<User> userToAdd = userSignUpService.signUp(newUser);
        if (userToAdd.isEmpty()) {
            model.addAttribute("message", "error");
            return "signUp";
        }
        return "redirect:/signIn";
    }

}
