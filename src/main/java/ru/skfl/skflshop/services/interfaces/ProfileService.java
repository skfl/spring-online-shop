package ru.skfl.skflshop.services.interfaces;

import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;

import java.util.List;

public interface ProfileService {
    List<UserOrder> getOrderByToken(String token);

    String getProfileName(String token);
}
