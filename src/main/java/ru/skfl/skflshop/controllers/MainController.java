package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.entities.Weapon;
import ru.skfl.skflshop.mappers.WeaponMapper;
import ru.skfl.skflshop.repositories.WeaponRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Weapon> weapons = weaponRepository.findAll();
        model.addAttribute("weaponList", weapons);
        return "index";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "cart";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/shipping")
    public String shipping(Model model) {
        return "shipping";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        return "contacts";
    }
}
