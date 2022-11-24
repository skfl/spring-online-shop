package ru.skfl.skflshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShippingController {
    @GetMapping("/shipping")
    public String shipping(Model model) {
        return "shipping";
    }
}
