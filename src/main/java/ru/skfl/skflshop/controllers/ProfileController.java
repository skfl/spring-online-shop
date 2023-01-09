package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.entities.UserOrder;
import ru.skfl.skflshop.services.implementations.TokenService;
import ru.skfl.skflshop.services.interfaces.OrderService;
import ru.skfl.skflshop.services.interfaces.ProfileService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpServletRequest request) {
        String userToken = tokenService.getTokenFromCookies(request.getCookies());
        String profileName = profileService.getProfileName(userToken);
        model.addAttribute("profileName", profileName);

        return "profilePage";
    }

    @PostMapping("/profile")
    @ResponseBody
    public List<UserOrderDTO> getCartData(HttpServletRequest request) {
        String userToken = tokenService.getTokenFromCookies(request.getCookies());
        return orderService.getOrderList(userToken);
    }
}
