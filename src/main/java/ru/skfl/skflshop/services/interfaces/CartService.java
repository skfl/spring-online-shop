package ru.skfl.skflshop.services.interfaces;

import org.springframework.stereotype.Service;
import ru.skfl.skflshop.dto.WeaponDTO;

import java.util.List;
import java.util.Map;


public interface CartService {
    List<WeaponDTO> getListOfCart(Map<Long, Integer> cartData);
}
