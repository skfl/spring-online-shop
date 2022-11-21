package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.services.interfaces.CartService;

import java.util.HashMap;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public List<WeaponDTO> cart(@RequestBody HashMap<Long, Integer> cartLocalStorage) {
        return cartService.getListOfCart(cartLocalStorage);
    }
}
