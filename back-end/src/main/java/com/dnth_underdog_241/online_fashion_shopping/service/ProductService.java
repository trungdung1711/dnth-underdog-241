package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.ProductUpdateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.ProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Brand;
import com.dnth_underdog_241.online_fashion_shopping.model.Category;
import com.dnth_underdog_241.online_fashion_shopping.model.Picture;
import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.CategoryEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.FileLocation;
import com.dnth_underdog_241.online_fashion_shopping.repository.BrandRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.CategoryRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService
{
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;
    private final ProductRepository productRepository;


    public void createProduct
            (
                    ProductCreateRequestDto productCreateRequestDto,
                    MultipartFile thumbnail,
                    MultipartFile picture1,
                    MultipartFile picture2,
                    MultipartFile picture3,
                    MultipartFile video,
                    Long categoryId
            ) throws IOException
    {
        Product product = productMapper.toProduct(productCreateRequestDto);

        Brand brand = brandRepository.findById(productCreateRequestDto.getBrandId()).get();
        Category category = categoryRepository.findById(categoryId).get();

        /*
            relationship
         */
        product.setBrand(brand);
        brand.getProducts().add(product);

        product.setCategory(category);
        category.getProducts().add(product);

        /*
            file saving
         */
        product.setThumbnail(fileService.uploadFile(thumbnail, FileLocation.PRODUCT));
        if (video != null)
        {
            product.setVideo(fileService.uploadFile(video, FileLocation.PRODUCT));
        }

        product.getPictures().add
                (
                    Picture
                            .builder()
                            .product(product)
                            .path(fileService.uploadFile(picture1, FileLocation.PRODUCT))
                            .build()
                );

        product.getPictures().add
                (
                    Picture
                            .builder()
                            .product(product)
                            .path(fileService.uploadFile(picture2, FileLocation.PRODUCT))
                            .build()
                );

        product.getPictures().add
                (
                    Picture
                            .builder()
                            .product(product)
                            .path(fileService.uploadFile(picture3, FileLocation.PRODUCT))
                            .build()
                );

        productRepository.save(product);

    }


    public void updateProduct
            (
                    ProductUpdateRequestDto productUpdateRequestDto,
                    MultipartFile thumbnail,
                    MultipartFile picture1,
                    MultipartFile picture2,
                    MultipartFile picture3,
                    MultipartFile video,
                    Long categoryId,
                    Long productId
            )
            throws IOException
    {
        Product product = productRepository.findProductById(productId);

        productMapper.updateProduct(productUpdateRequestDto, product);

        if (thumbnail != null && !thumbnail.isEmpty())
            product.setThumbnail(fileService.uploadFile(thumbnail, FileLocation.PRODUCT));

        if (picture1 != null && !picture1.isEmpty())
        {
            product.getPictures().set(0,
                    Picture
                            .builder()
                            .product(product)
                            .path(fileService.uploadFile(picture1, FileLocation.PRODUCT))
                            .build());
        }

        if (picture2 != null && !picture2.isEmpty())
        {
            product.getPictures().set(1,
                    Picture
                            .builder()
                            .product(product)
                            .path(fileService.uploadFile(picture2, FileLocation.PRODUCT))
                            .build());
        }

        if (picture3 != null && !picture3.isEmpty())
        {
            product.getPictures().set(2,
                    Picture
                            .builder()
                            .product(product)
                            .path(fileService.uploadFile(picture3, FileLocation.PRODUCT))
                            .build());
        }

        if (video != null && !video.isEmpty())
            product.setVideo(fileService.uploadFile(video, FileLocation.PRODUCT));
    }


    public Page<ProductGetAllResponseDto> getAllProducts(int page, int size)
    {
        Page<Product> productPage = productRepository.findAllProduct(PageRequest.of(page, size));
        return productPage.map(productMapper::toProductGetAllDto);
    }


    public Page<ProductGetAllResponseDto> getAllProductsByCategory(int page, int size, Long categoryId)
    {
        Page<Product> productPage = productRepository.findAllByCategoryId(PageRequest.of(page, size), categoryId);
        return productPage.map(productMapper::toProductGetAllDto);
    }


    public Page<ProductGetAllResponseDto> getAllProductsByBrand(int page, int size, Long brandId)
    {
        Page<Product> productPage = productRepository.findAllByBrandId(PageRequest.of(page, size), brandId);
        return productPage.map(productMapper::toProductGetAllDto);
    }


    public ProductGetResponseDto getProductById(Long productId)
    {
        return productMapper
                .toProductGetDto
                        (
                                productRepository
                                        .findById(productId)
                                        .orElseThrow
                                                (
                                                        () -> new ResourcesNotFound("Product not found")
                                                )
                        );
    }



    public void deleteProduct(Long productId)
    {

        productRepository.removeProductById(productId);
    }
}