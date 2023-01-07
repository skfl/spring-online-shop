package ru.skfl.skflshop.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.entities.Weapon;
import ru.skfl.skflshop.mappers.WeaponMapper;
import ru.skfl.skflshop.repositories.WeaponRepository;
import ru.skfl.skflshop.services.interfaces.CartService;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private WeaponRepository weaponRepository;

    @Override
    public List<WeaponDTO> getListOfCart(Map<Long, Integer> cartData) {
        List<WeaponDTO> weaponList = new ArrayList<>();

        for (Long itemID : cartData.keySet()) {
            Optional<Weapon> weapon = weaponRepository.findById(itemID);
            weapon.ifPresent(value -> weaponList.add(WeaponMapper.INSTANCE.toDTO(value)));
        }

        return weaponList;
    }
}
