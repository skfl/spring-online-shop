package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skfl.skflshop.dto.OrderDTO;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.entities.UserOrder;
import ru.skfl.skflshop.services.interfaces.CartService;

import java.util.HashMap;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("userOrder",new OrderDTO());
        return "cart";
    }

    @PostMapping("/cart")
    @ResponseBody
    public List<WeaponDTO> getCartData(@RequestBody HashMap<Long, Integer> cartLocalStorage) {
        return cartService.getListOfCart(cartLocalStorage);
    }

}
