package com.dnth_underdog_241.online_fashion_shopping.mapper.impl;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.CartProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CartProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.CartProduct;
import org.springframework.stereotype.Component;


@Component
public class CartProductMapperImpl implements CartProductMapper
{
    @Override
    public CartProductGetResponseDto toCartProductGetResponseDto(CartProduct cartProduct)
    {
        return CartProductGetResponseDto
                .builder()
                .id(cartProduct.getId())
                .cartid(cartProduct.getCart().getId())
                .productId(cartProduct.getVariantProduct().getProduct().getId())
                .variantProductId(cartProduct.getVariantProduct().getId())
                .name(cartProduct.getVariantProduct().getProduct().getName())
                .thumbnail(cartProduct.getVariantProduct().getProduct().getThumbnail())
                .shortDescription(cartProduct.getVariantProduct().getProduct().getShortDescription())
                .price(cartProduct.getVariantProduct().getProduct().getPrice())
                .quantity(cartProduct.getQuantity())
                .size(cartProduct.getVariantProduct().getSize().getSize())
                .colour(cartProduct.getVariantProduct().getColour().getColour())
                .timeStamp(cartProduct.getTimeStamp())
                .build();
    }
}