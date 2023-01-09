package ru.skfl.skflshop.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;
import ru.skfl.skflshop.mappers.OrderMapper;
import ru.skfl.skflshop.repositories.OrderRepository;
import ru.skfl.skflshop.repositories.UserRepository;
import ru.skfl.skflshop.services.interfaces.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<UserOrderDTO> getOrderList(String token) {
        Optional<User> user = userRepository.findByToken(token);
        if (user.isPresent()) {
            List<UserOrder> orders = orderRepository.getUserOrdersByUserId(user.get().getId()).orElse(null);
            List<UserOrderDTO> orderDTOS = new ArrayList<>();
            for (UserOrder order : orders) {
                orderDTOS.add(OrderMapper.INSTANCE.toDTO(order));
            }
            return orderDTOS;
        }
        return null;
    }
}
