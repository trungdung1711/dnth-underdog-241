package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.VariantProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.VariantProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.VariantProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.*;
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
    private final ImportTransactionService importTransactionService;


    public void createOrAddVariantProduct(VariantProductCreateRequestDto variantProductCreateRequestDto, MultipartFile picture) throws IOException
    {
        Size size = sizeRepository.findById(variantProductCreateRequestDto.getSize()).orElseThrow(() -> new ResourcesNotFound("Size not found"));
        Colour colour = colourRepository.findById(variantProductCreateRequestDto.getColour()).orElseThrow(() -> new ResourcesNotFound("Colour not found"));


        if (variantProductRepository.existsByProductIdAndSizeAndColour(variantProductCreateRequestDto.getId(), size, colour))
        {
            VariantProduct variantProduct = variantProductRepository.findByProductIdAndSizeAndColour(variantProductCreateRequestDto.getId(), size.getSize(), colour.getColour()).get();

            variantProduct.setStock(variantProduct.getStock() + variantProductCreateRequestDto.getStock());

            importTransactionService.createImportTransaction(variantProduct.getProduct().getPrice(), variantProductCreateRequestDto.getStock(), variantProduct);
            return;
        }
main

        Product product = productRepository.findProductById(variantProductCreateRequestDto.getId());

        VariantProduct variantProduct = variantProductMapper.toVariantProduct(variantProductCreateRequestDto);

        variantProduct.setProduct(product);

        variantProduct.setSize(size);

        variantProduct.setColour(colour);

        variantProduct.setPicture(fileService.uploadFile(picture, FileLocation.PRODUCT));

        variantProductRepository.save(variantProduct);

        importTransactionService.createImportTransaction(variantProduct.getProduct().getPrice(), variantProduct.getStock(), variantProduct);
    }


    public List<VariantProductGetResponseDto> getVariantProduct(Long productId)
    {
        List<VariantProduct> variantProduct = variantProductRepository.findByProductId(productId);

        return variantProduct.stream()
                .map(variantProductMapper::toVariantProductGetResponseDto)
                .collect(Collectors.toList());
    }

}