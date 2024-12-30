package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.VariantProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.VariantProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourceAlreadyExistException;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.VariantProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Colour;
import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import com.dnth_underdog_241.online_fashion_shopping.model.Size;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.FileLocation;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.ColourRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.ProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.SizeRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.VariantProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class VariantProductService
{
    private final ProductRepository productRepository;
    private final VariantProductMapper variantProductMapper;
    private final SizeRepository sizeRepository;
    private final ColourRepository colourRepository;
    private final FileService fileService;
    private final VariantProductRepository variantProductRepository;


    public void createVariantProduct(VariantProductCreateRequestDto variantProductCreateRequestDto, MultipartFile picture) throws IOException
    {
        Size size = sizeRepository.findById(variantProductCreateRequestDto.getSize()).orElseThrow(() -> new ResourcesNotFound("Size not found"));
        Colour colour = colourRepository.findById(variantProductCreateRequestDto.getColour()).orElseThrow(() -> new ResourcesNotFound("Colour not found"));

        if (variantProductRepository.existsByProductId(variantProductCreateRequestDto.getId()))
            throw new ResourceAlreadyExistException("Product variant already exists");

        Product product = productRepository.findProductById(variantProductCreateRequestDto.getId());

        VariantProduct variantProduct = variantProductMapper.toVariantProduct(variantProductCreateRequestDto);

        variantProduct.setProduct(product);

        variantProduct.setSize(size);

        variantProduct.setColour(colour);

        variantProduct.setPicture(fileService.uploadFile(picture, FileLocation.PRODUCT));

        variantProductRepository.save(variantProduct);
    }


    public List<VariantProductGetResponseDto> getVariantProduct(Long productId)
    {
        List<VariantProduct> variantProduct = variantProductRepository.findByProductId(productId);

        return variantProduct.stream()
                .map(variantProductMapper::toVariantProductGetResponseDto)
                .collect(Collectors.toList());
    }

}