package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandGetDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.BrandCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ImageRequiredException;
import com.dnth_underdog_241.online_fashion_shopping.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
public class BrandController
{
    private final BrandService brandService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('EMPLOYEE')")
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
}
