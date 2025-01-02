package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.VariantProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.VariantProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import org.springframework.stereotype.Component;


@Component
public interface VariantProductMapper
{
    VariantProduct toVariantProduct(VariantProductCreateRequestDto variantProductCreateRequestDto);


    VariantProductGetResponseDto toVariantProductGetResponseDto(VariantProduct variantProduct);
}
