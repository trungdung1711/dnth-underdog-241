package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandGetDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.BrandCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.CategoryGetDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ImageRequiredException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.BrandMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import com.dnth_underdog_241.online_fashion_shopping.model.Category;
import com.dnth_underdog_241.online_fashion_shopping.repository.BrandRepository;
import com.dnth_underdog_241.online_fashion_shopping.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
public class BrandController
{
    private final BrandService brandService;
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<BrandCreateResponseDto> createBrand
            (
                    @RequestPart(name = "brand") BrandCreateRequestDto brandUploadBrandRequestDto,
                    @RequestPart(name = "image") MultipartFile image
            )
            throws IOException
    {
        if (image.isEmpty())
        {
            throw new ImageRequiredException();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandService.createBrand(brandUploadBrandRequestDto, image));
    }


    @GetMapping("{name}")
    public ResponseEntity<BrandGetDto> getBranchByName(@PathVariable String name)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getDetailedBrandByName(name));
    }

    @GetMapping
    public ResponseEntity<List<BrandGetDto>> getBranchAll()
    {
        List<Brand> brands = brandRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        brands
                                .stream()
                                .map(brandMapper::toDto2)
                                .collect(Collectors.toList())
                );
    }
}
