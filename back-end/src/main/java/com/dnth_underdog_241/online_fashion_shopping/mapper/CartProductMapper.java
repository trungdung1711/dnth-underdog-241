package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.CartProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.CartProduct;
import org.springframework.stereotype.Component;


@Component
public interface CartProductMapper
{
    CartProductGetResponseDto toCartProductGetResponseDto(CartProduct cartProduct);
}
