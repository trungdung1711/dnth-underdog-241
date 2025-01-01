package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderItemGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemMapper
{
    OrderItemGetResponseDto toOrderItemGetResponseDto(OrderItem orderItem);
}
