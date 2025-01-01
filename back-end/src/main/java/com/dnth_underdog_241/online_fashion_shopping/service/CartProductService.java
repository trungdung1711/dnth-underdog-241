package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.CartProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.exception.StockUnavailableException;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CartProductMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.CartProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.Colour;
import com.dnth_underdog_241.online_fashion_shopping.model.Size;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.CartProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.VariantProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Transactional
public class CartProductService
{
    private final CartProductRepository cartProductRepository;
    private final CartProductMapper cartProductMapper;
    private final WebUserRepository webUserRepository;
    private final VariantProductRepository variantProductRepository;

    public enum Color1 {
        BEIGE,
        BLACK,
        DARK_GREEN,
        GRAY,
        GREEN,
        NAVY,
        PINK,
        WHITE,
        WINE;
    }

    public enum Size1 {
        S,     // Small
        M,     // Medium
        L,     // Large
        XL,    // Extra Large
        XXL    // Double Extra Large
    }



    public void addCartProduct(Long id, Long variantProductId, Long quantity, Colour color, Size size)
    {
        WebUser webUser = webUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Customer customer = (Customer) webUser;

        VariantProduct variantProduct = variantProductRepository.findByIdProduct(variantProductId, color, size);

        if(variantProduct == null){
            throw new ResourcesNotFound("Variant Product not found");
        }

        if (quantity > variantProduct.getStock())
            throw new StockUnavailableException("Stock unavailable");

        if (cartProductRepository.existsByVariantProductIdAndCartId(variantProductId, customer.getCart().getId()))
        {
            long total = cartProductRepository.findByCustomerIdAndVariantProductId(id, variantProductId).getQuantity() + quantity;

            if (total > variantProduct.getStock())
                throw new StockUnavailableException("Stock unavailable");

            CartProduct cartProduct = cartProductRepository.findByCustomerIdAndVariantProductId(id, variantProductId);

            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        }
        else
        {
            CartProduct cartProduct = CartProduct
                    .builder()
                    .cart(customer.getCart())
                    .variantProduct(variantProduct)
                    .price(variantProduct.getProduct().getPrice())
                    .quantity(quantity)
                    .timeStamp(LocalDateTime.now())
                    .build();

            cartProductRepository.save(cartProduct);

            customer
                    .getCart()
                    .getCartProducts()
                    .add(cartProduct);

            variantProduct
                    .getCartProducts()
                    .add(cartProduct);
        }
    }


    public Page<CartProductGetResponseDto> getCartProducts(int page, int size, Long id)
    {
        Page<CartProduct> cartProductPage = cartProductRepository.findByCustomerId(id, PageRequest.of(page, size));
        return cartProductPage.map(cartProductMapper::toCartProductGetResponseDto);
    }


    public void deleteCartProduct(Long id)
    {
        cartProductRepository.deleteById(id);
    }
}