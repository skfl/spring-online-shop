package ru.skfl.skflshop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skfl.skflshop.dto.UserOrderDTO;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    UserOrderDTO toDTO(UserOrder userOrder);

    UserOrder toEntity(UserOrderDTO userOrderDTO);
}
