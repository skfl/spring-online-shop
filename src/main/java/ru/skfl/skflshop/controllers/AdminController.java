package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skfl.skflshop.entities.Weapon;
import ru.skfl.skflshop.repositories.WeaponRepository;

@Controller
public class AdminController {

    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping("/admin")
    public String addWeapon(Model model) {
        return "adminPage";
    }
}
