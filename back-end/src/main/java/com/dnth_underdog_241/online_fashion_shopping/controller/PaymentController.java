package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.PayPalOrderCaptureResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.PayPalOrderCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("api/v1/payment/paypal/orders")
@RequiredArgsConstructor
public class PaymentController
{
    private final PaymentService paymentService;


    @PostMapping("{orderId}")
    public ResponseEntity<PayPalOrderCreateResponseDto> startCheckOut(@PathVariable Long orderId) throws Exception
    {
        log.info("Payment controller check out");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.startCheckOut(orderId));
    }


    @PostMapping("{orderId}/capture")
    public ResponseEntity<PayPalOrderCaptureResponseDto> captureCheckOut(@PathVariable String orderId) throws Exception
    {
        log.info("Payment controller capture");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentService.captureOrder(orderId));
    }
}