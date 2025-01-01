package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandGetDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.BrandCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandCreateResponseDto;
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
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class BrandService
{
    private final BrandMapper brandMapper;


    private final FileService fileService;


    private final BrandRepository brandRepository;


    public BrandCreateResponseDto createBrand(BrandCreateRequestDto brandUploadBrandRequestDto, MultipartFile image)
            throws IOException
    {
        String url = fileService.uploadFile(image, FileLocation.BRAND);
        Brand brand = brandMapper.toEntity(brandUploadBrandRequestDto);
        brand.setUrl(url);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toDto1(savedBrand);
    }


    public BrandGetDto getDetailedBrandByName(String name)
    {
        Brand brand = brandRepository.findBrandByName(name).orElseThrow(() -> new ResourcesNotFound("Brand not found"));
        return brandMapper.toDto2(brand);
    }

}
