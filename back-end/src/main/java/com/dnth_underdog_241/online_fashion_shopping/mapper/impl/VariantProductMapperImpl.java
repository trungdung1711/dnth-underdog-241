package com.dnth_underdog_241.online_fashion_shopping.mapper.impl;

import com.dnth_underdog_241.online_fashion_shopping.dto.request.VariantProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.VariantProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.VariantProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import org.springframework.stereotype.Component;


@Component
public class VariantProductMapperImpl implements VariantProductMapper
{

    @Override
    public VariantProduct toVariantProduct(VariantProductCreateRequestDto variantProductCreateRequestDto)
    {
        return VariantProduct
                .builder()
                .stock(variantProductCreateRequestDto.getStock())
                .build();
    }

    @Override
    public VariantProductGetResponseDto toVariantProductGetResponseDto(VariantProduct variantProduct)
    {
        return VariantProductGetResponseDto
                .builder()
                .id(variantProduct.getId())
                .stock(variantProduct.getStock())
                .picture(variantProduct.getPicture())
                .size(variantProduct.getSize().getSize())
                .colour(variantProduct.getColour().getColour())
                .build();
    }
}