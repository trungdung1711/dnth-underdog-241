package com.dnth_underdog_241.online_fashion_shopping.mapper;

import com.dnth_underdog_241.online_fashion_shopping.dto.request.OrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import org.springframework.stereotype.Component;


@Component
public interface OrderMapper
{
    Order toOrder(OrderCreateRequestDto orderCreateRequestDto);


    OrderCreateResponseDto toOrderCreateResponseDto(Order order);


    OrderGetAllResponseDto toOrderGetAllResponseDto(Order order);


    OrderGetResponseDto toOrderGetResponseDto(Order order);
}
