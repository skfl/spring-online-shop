package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.skfl.skflshop.entities.Weapon;
import ru.skfl.skflshop.repositories.WeaponRepository;

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
}
