package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.VariantProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.BrandGetDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.VariantProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.ProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.mapper.VariantProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.Material;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.ProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.VariantProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.service.ProductService;
import com.dnth_underdog_241.online_fashion_shopping.service.VariantProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductAllController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;



    @GetMapping
    public ResponseEntity<List<ProductGetAllResponseDto>> getBranchAll()
    {
        List<Product> brands = productRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        brands
                                .stream()
                                .map(productMapper::toProductGetAllDto)
                                .collect(Collectors.toList())
                );
    }

    private final ProductService productService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<Void> createProduct
            (
                    @RequestPart("product") ProductCreateRequestDto productCreateRequestDto,
                    @RequestPart("thumbnail") MultipartFile thumbnail,
                    @RequestPart("picture1") MultipartFile picture1,
                    @RequestPart("picture2") MultipartFile picture2,
                    @RequestPart("picture3") MultipartFile picture3,
                    @RequestPart(value = "video", required = false) MultipartFile video,
                    @RequestPart("categoryId") Long categoryId
            ) throws IOException
    {

        productService.createProduct(productCreateRequestDto, thumbnail, picture1, picture2, picture3, video, categoryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetResponseDto> getProduct(@PathVariable Long productId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body
                        (
                                productService.getProductById(productId)
                        );
    }

    private final VariantProductService variantProductService;
    private  final VariantProductRepository variantProductRepository;
    private  final VariantProductMapper variantProductMapper;

    @PostMapping(value = "{productId}/variant", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<Void> addProductVariant
            (
                    @RequestPart("variant") VariantProductCreateRequestDto variantProductCreateRequestDto,
                    @RequestPart("picture") MultipartFile picture
            ) throws IOException
    {
        variantProductService.createVariantProduct(variantProductCreateRequestDto, picture);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("{productId}/variant")
    public ResponseEntity<List<VariantProductGetResponseDto>>  getProductVariant
            (
                    @PathVariable Long productId
            )
    {
        List<VariantProductGetResponseDto> variants = variantProductService.getVariantProduct(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        variants
                );
    }

}
