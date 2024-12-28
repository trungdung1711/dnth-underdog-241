package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/categories/{categoryId}/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Void> createProduct
            (
                    @RequestPart("product") ProductCreateRequestDto productCreateRequestDto,
                    @RequestPart("thumbnail") MultipartFile thumbnail,
                    @RequestPart("picture1") MultipartFile picture1,
                    @RequestPart("picture2") MultipartFile picture2,
                    @RequestPart("picture3") MultipartFile picture3,
                    @RequestPart(value = "video", required = false) MultipartFile video,
                    @PathVariable Long categoryId
            ) throws IOException
    {
        productService.createProduct(productCreateRequestDto, thumbnail, picture1, picture2, picture3, video, categoryId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }


    @PostMapping
    @PostAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Void> createVariantProduct()
    {
        return null;
    }


    @GetMapping()
    public ResponseEntity<Page<ProductGetAllResponseDto>> getAllProducts
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size,
                    @PathVariable Long categoryId
            )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts(page, size, categoryId));
    }


    @GetMapping("{productId}")
    public ResponseEntity<ProductGetResponseDto> getProduct(@PathVariable Long productId, @PathVariable String categoryId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body
                        (
                                productService.getProductById(productId)
                        );
    }
}