package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductUpdateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import org.springframework.stereotype.Component;


@Component
public interface ProductMapper
{
    Product toProduct(ProductCreateRequestDto productCreateRequestDto);

    ProductGetAllResponseDto toProductGetAllDto(Product product);

    ProductGetResponseDto toProductGetDto(Product product);

    void updateProduct(ProductUpdateRequestDto productUpdateRequestDto, Product product);
}