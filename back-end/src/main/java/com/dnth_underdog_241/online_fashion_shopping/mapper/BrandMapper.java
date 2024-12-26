package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandGetDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.BrandCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BrandMapper
{
    Brand toEntity(BrandCreateRequestDto brandUploadBrandRequestDto);

    BrandCreateResponseDto toDto1(Brand brand);

    BrandGetDto toDto2(Brand brand);

}
