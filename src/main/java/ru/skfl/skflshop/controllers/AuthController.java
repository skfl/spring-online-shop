package ru.skfl.skflshop.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping("/signIn/do")
    public String authUser() {
        return "redirect:/profile";
    }
}
