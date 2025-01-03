package com.dnth_underdog_241.online_fashion_shopping.mapper.impl;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderItemGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.OrderItemMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.OrderItem;
import org.springframework.stereotype.Component;


@Component
public class OrderItemMapperImpl implements OrderItemMapper
{
    @Override
    public OrderItemGetResponseDto toOrderItemGetResponseDto(OrderItem orderItem)
    {
        return OrderItemGetResponseDto
                .builder()
                .id(orderItem.getId())
                .name(orderItem.getName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .size(orderItem.getSizeEnum())
                .colour(orderItem.getColourEnum())
                .image(orderItem.getImage())
                .build();
    }
}
