package com.dnth_underdog_241.online_fashion_shopping.mapper.impl;

import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductUpdateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.ProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import com.dnth_underdog_241.online_fashion_shopping.model.Picture;
import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import com.dnth_underdog_241.online_fashion_shopping.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper
{
    @Override
    public Product toProduct(ProductCreateRequestDto productCreateRequestDto)
    {
        return
                Product
                        .builder()
                        .price(productCreateRequestDto.getPrice())
                        .material(productCreateRequestDto.getMaterial())
                        .shortDescription(productCreateRequestDto.getShortDescription())
                        .longDescription(productCreateRequestDto.getLongDescription())
                        .name(productCreateRequestDto.getName())
                        .pictures(new ArrayList<>())
                        .reviews(new ArrayList<>())
                        .variantProducts(new ArrayList<>())
                        .build();
    }


    @Override
    public ProductGetAllResponseDto toProductGetAllDto(Product product)
    {
        return
                ProductGetAllResponseDto
                        .builder()
                        .id(product.getId())
                        .price(product.getPrice())
                        .material(product.getMaterial())
                        .shortDescription(product.getShortDescription())
                        .name(product.getName())
                        .thumbnail(product.getThumbnail())
                        .brand(product.getBrand().getName())
                        .category(product.getCategory().getName())
                        .build();
    }


    @Override
    public ProductGetResponseDto toProductGetDto(Product product)
    {
        return ProductGetResponseDto
                .builder()
                .id(product.getId())
                .price(product.getPrice())
                .material(product.getMaterial())
                .shortDescription(product.getShortDescription())
                .longDescription(product.getLongDescription())
                .name(product.getName())
                .thumbnail(product.getThumbnail())
                .video(product.getVideo())
                .pictures
                        (
                                product
                                        .getPictures()
                                        .stream()
                                        .map
                                                (
                                                        Picture::getPath
                                                )
                                        .collect(Collectors.toList())
                        )
                .brand(product.getBrand().getName())
                .category(product.getCategory().getName())
                .build();
    }


    @Override
    public void updateProduct(ProductUpdateRequestDto productUpdateRequestDto, Product product)
    {
        if (productUpdateRequestDto.getPrice() != null)
            product.setPrice(productUpdateRequestDto.getPrice());
        if (productUpdateRequestDto.getMaterial() != null)
            product.setMaterial(productUpdateRequestDto.getMaterial());
        if (productUpdateRequestDto.getShortDescription() != null)
            product.setShortDescription(productUpdateRequestDto.getShortDescription());
        if (productUpdateRequestDto.getLongDescription() != null)
            product.setLongDescription(productUpdateRequestDto.getLongDescription());
        if (productUpdateRequestDto.getName() != null)
            product.setName(productUpdateRequestDto.getName());
    }
}