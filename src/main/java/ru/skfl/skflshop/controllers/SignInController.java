package ru.skfl.skflshop.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skfl.skflshop.dto.SignInForm;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String getLoginPage(Model model, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        model.addAttribute("signInForm", new SignInForm());
        model.addAttribute("message", "");
        return "signIn";
    }
}
