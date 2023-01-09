package ru.skfl.skflshop.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;
import ru.skfl.skflshop.mappers.OrderMapper;
import ru.skfl.skflshop.repositories.OrderRepository;
import ru.skfl.skflshop.repositories.UserRepository;
import ru.skfl.skflshop.services.interfaces.OrderConfirmationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class OrderConfirmationServiceImpl implements OrderConfirmationService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserOrder submitOrder(UserOrderDTO userOrderDTO, HttpServletRequest request) {
        Optional<User> user = userRepository.findByToken(tokenService.getTokenFromCookies(request.getCookies()));
        if (user.isEmpty()) {
            throw new IllegalArgumentException("cant find usr by token while submit order");
        }
        System.err.println(userOrderDTO.toString());
        UserOrder order = UserOrder.builder()
                .name(userOrderDTO.getName())
                .email(userOrderDTO.getEmail())
                .address(userOrderDTO.getAddress())
                .user(user.get())
                .items(userOrderDTO.getItems())
                .build();
        return orderRepository.save(order);
    }
}
