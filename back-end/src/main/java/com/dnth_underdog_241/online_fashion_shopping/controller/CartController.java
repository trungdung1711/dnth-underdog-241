package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.CartProductGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Colour;
import com.dnth_underdog_241.online_fashion_shopping.model.Size;
import com.dnth_underdog_241.online_fashion_shopping.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customers/{id}/cart")
@RequiredArgsConstructor
public class CartController
{
    private final CartProductService cartProductService;


    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Page<CartProductGetResponseDto>> getProductFromCart
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size,
                    @PathVariable Long id
            )
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartProductService.getCartProducts(page, size, id));
    }


    
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public <color> ResponseEntity<Void> addProductToCart
            (
                    @PathVariable Long id,
                    @RequestParam Long variantProductId,
                    @RequestParam Long quantity,
                    @RequestParam Colour color,
                    @RequestParam Size size
            )
    {
        cartProductService.addCartProduct(id, variantProductId, quantity, color, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }


    @DeleteMapping("{cartProductId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> deleteProductFromCart
            (
                    @PathVariable Long id,
                    @PathVariable Long cartProductId
            )
    {
        cartProductService.deleteCartProduct(cartProductId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }
}
