package ru.skfl.skflshop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skfl.skflshop.dto.WeaponDTO;
import ru.skfl.skflshop.entities.Weapon;

@Mapper
public interface WeaponMapper {
    WeaponMapper INSTANCE = Mappers.getMapper(WeaponMapper.class);

    WeaponDTO weaponToWeaponDTO(Weapon weapon);

    Weapon weaponDTOtoWeapon(WeaponDTO weaponDTO);
}
