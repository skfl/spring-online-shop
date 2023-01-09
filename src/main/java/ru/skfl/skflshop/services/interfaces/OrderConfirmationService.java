package ru.skfl.skflshop.services.interfaces;

import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.entities.UserOrder;

import javax.servlet.http.HttpServletRequest;

public interface OrderConfirmationService {

    UserOrder submitOrder(UserOrderDTO userOrderDTO, HttpServletRequest request);
}
