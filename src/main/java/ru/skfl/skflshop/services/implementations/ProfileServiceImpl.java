package ru.skfl.skflshop.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;
import ru.skfl.skflshop.repositories.OrderRepository;
import ru.skfl.skflshop.repositories.UserRepository;
import ru.skfl.skflshop.services.interfaces.ProfileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<UserOrder> getOrderByToken(String token) {
        List<UserOrder> orders = new ArrayList<>();
        Optional<User> user = userRepository.findByToken(token);
        if (user.isEmpty()){
            throw new IllegalArgumentException("cant find user by token while getting order list");
        }
        orders=orderRepository.findByUser(user.get());
        return orders;
    }

    @Override
    public String getProfileName(String token) {
        Optional<User> profile = userRepository.findByToken(token);
        if (profile.isPresent()) {
            return profile.get().getUsername();
        } else {
            throw new IllegalArgumentException("There is no user by this token");
        }
    }
}
