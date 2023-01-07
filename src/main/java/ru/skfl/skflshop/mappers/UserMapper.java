package ru.skfl.skflshop.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skfl.skflshop.dto.UserDTO;
import ru.skfl.skflshop.entities.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
