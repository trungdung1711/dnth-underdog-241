package com.dnth_underdog_241.online_fashion_shopping.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.OrderService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/orders")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminOrderController 
{
    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<Page<OrderGetAllResponseDto>> getAllOrders(Pageable pageable)
    {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(orderService.getAllOrders(pageable));
    }   
    
    
    @GetMapping("{orderId}")
    public ResponseEntity<OrderGetResponseDto> getOrder(@PathVariable("orderId") Long orderId)
    {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(orderService.getOrder(orderId));
    }
}
