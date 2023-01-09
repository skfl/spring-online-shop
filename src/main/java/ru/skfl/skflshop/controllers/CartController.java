package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.services.implementations.TokenService;
import ru.skfl.skflshop.services.interfaces.CartService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/cart")
    public String cart(Model model, HttpServletRequest request) {
        if (tokenService.getTokenFromCookies(request.getCookies()).equals("")) {
            model.addAttribute("message", "error");
        } else {
            model.addAttribute("message", "");
        }
        model.addAttribute("userOrder", new UserOrderDTO());
        return "cart";
    }

    @PostMapping("/cart")
    @ResponseBody
    public List<WeaponDTO> getCartData(@RequestBody HashMap<Long, Integer> cartLocalStorage) {
        return cartService.getListOfCart(cartLocalStorage);
    }
}
