package ru.skfl.skflshop.services.interfaces;

import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;

import java.util.List;

public interface OrderService {
    List<UserOrderDTO> getOrderList(String token);

}
