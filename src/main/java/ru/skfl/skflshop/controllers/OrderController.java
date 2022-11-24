package ru.skfl.skflshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skfl.skflshop.dto.OrderDTO;

@Controller
public class OrderController {

    @PostMapping("/order-confirmation")
    public String getOrder(@ModelAttribute OrderDTO orderDTO, Model model) {
        System.out.println(orderDTO.toString());
        return "order-confirmation";
    }
}
