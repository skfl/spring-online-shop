package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skfl.skflshop.entities.Weapon;
import ru.skfl.skflshop.repositories.WeaponRepository;

@RestController
public class AdminController {

    @Autowired
    private WeaponRepository weaponRepository;

    @PostMapping("/add")
    public String addWeapon(@RequestBody Weapon weapon) {
        weaponRepository.save(weapon);
        return "kk";
    }
}
