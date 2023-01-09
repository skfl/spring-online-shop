package ru.skfl.skflshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.entities.UserOrder;
import ru.skfl.skflshop.services.interfaces.OrderConfirmationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderConfirmationController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;

    @PostMapping("/order-confirmation")
    public String submitOrder(@ModelAttribute UserOrderDTO orderDTO, Model model, HttpServletRequest request) {
        UserOrder order = orderConfirmationService.submitOrder(orderDTO, request);
        if (order != null) {
            model.addAttribute("message", "success");
        } else {
            model.addAttribute("message", "failure");
        }
        return "order-confirmation";
    }
}
