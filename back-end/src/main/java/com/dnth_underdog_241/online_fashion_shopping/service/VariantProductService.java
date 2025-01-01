package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.VariantProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.VariantProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourceAlreadyExistException;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.VariantProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.*;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.FileLocation;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SupplierEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.ColourRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.ProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.SizeRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.VariantProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;


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


    public void createOrAddVariantProduct(VariantProductCreateRequestDto variantProductCreateRequestDto, MultipartFile picture) throws IOException
    {
        Size size = sizeRepository.findById(variantProductCreateRequestDto.getSize()).orElseThrow(() -> new ResourcesNotFound("Size not found"));
        Colour colour = colourRepository.findById(variantProductCreateRequestDto.getColour()).orElseThrow(() -> new ResourcesNotFound("Colour not found"));

        ImportTransaction importTransaction = new ImportTransaction();

        importTransaction.setDate(LocalDate.now());

        if (variantProductRepository.existsByProductIdAndSizeAndColour(variantProductCreateRequestDto.getId(), size, colour))
        {
            VariantProduct variantProduct = variantProductRepository.findByProductIdAndSizeAndColour(variantProductCreateRequestDto.getId(), size.getSize(), colour.getColour()).get();

            variantProduct.setStock(variantProduct.getStock() + variantProductCreateRequestDto.getStock());


            importTransaction.setStatus("SUCCESSFUL");
            importTransaction.setSupplierEnum(SupplierEnum.getRandomSupplier());

            importTransaction.setUnitCost();

            return;
        }

        Product product = productRepository.findProductById(variantProductCreateRequestDto.getId());

        VariantProduct variantProduct = variantProductMapper.toVariantProduct(variantProductCreateRequestDto);

        variantProduct.setProduct(product);
        product.getVariantProducts().add(variantProduct);

        variantProduct.setSize(size);
        variantProduct.setColour(colour);

        variantProduct.setPicture(fileService.uploadFile(picture, FileLocation.PRODUCT));

        variantProductRepository.save(variantProduct);
    }


    public VariantProductGetResponseDto getVariantProduct(Long productId, SizeEnum size, ColourEnum colour)
    {
        VariantProduct variantProduct = variantProductRepository.findByProductIdAndSizeAndColour(productId, size, colour).orElseThrow(() -> new ResourcesNotFound("Product not found"));

        return variantProductMapper.toVariantProductGetResponseDto(variantProduct);
    }
}