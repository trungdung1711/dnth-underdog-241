package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.OrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customers/{id}/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;


    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER') and authentication.principal.getId() == #id ")
    public ResponseEntity<OrderCreateResponseDto> createOrder(@PathVariable("id") Long id, @RequestBody @Valid OrderCreateRequestDto orderCreateRequestDto)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body
                        (
                                orderService.createOrder(orderCreateRequestDto)
                        );
    }


    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER') and authentication.principal.getId() == #id")
    public ResponseEntity<Page<OrderGetAllResponseDto>> getAllOrders(@PathVariable("id") Long id, Pageable pageable)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders(id, pageable));
    }


    @GetMapping("{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') and authentication.principal.getId() == #id")
    public ResponseEntity<OrderGetResponseDto> getOrder(@PathVariable("id") Long id, @PathVariable Long orderId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrder(orderId));
    }


    @DeleteMapping("{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') and authentication.principal.getId() == #id")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id, @PathVariable Long orderId)
    {
        orderService.deleteOrder(orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}