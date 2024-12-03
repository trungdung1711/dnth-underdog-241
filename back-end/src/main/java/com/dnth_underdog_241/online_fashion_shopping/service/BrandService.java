package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.BrandGetDetailedBrandResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.BrandUploadBrandRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.BrandUploadBrandResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.BrandMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.FileLocation;
import com.dnth_underdog_241.online_fashion_shopping.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class BrandService
{
    private final BrandMapper brandMapper;


    private final FileService fileService;


    private final BrandRepository brandRepository;


    public BrandUploadBrandResponseDto createBrand(BrandUploadBrandRequestDto brandUploadBrandRequestDto, MultipartFile image)
            throws IOException
    {
        String url = fileService.uploadFile(image, FileLocation.BRAND);
        Brand brand = brandMapper.toEntity(brandUploadBrandRequestDto);
        brand.setUrl(url);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toDto1(savedBrand);
    }


    public BrandGetDetailedBrandResponseDto getDetailedBrandByName(String name)
    {
        Brand brand = brandRepository.findBrandByName(name).orElseThrow(() -> new ResourcesNotFound("Brand not found"));
        return brandMapper.toDto2(brand);
    }
}
