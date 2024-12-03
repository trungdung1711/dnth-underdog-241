package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.BrandGetDetailedBrandResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.BrandUploadBrandRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.BrandUploadBrandResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BrandMapper
{
    Brand toEntity(BrandUploadBrandRequestDto brandUploadBrandRequestDto);

    BrandUploadBrandResponseDto toDto1(Brand brand);

    BrandGetDetailedBrandResponseDto toDto2(Brand brand);
}
