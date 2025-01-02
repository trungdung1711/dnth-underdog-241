package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.PayPalOrderCaptureResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.PayPalHttpClient;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.request.PayPalOrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.PayPalOrderCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.PayPalOrderMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.OrderStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.PaymentStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService
{
    private final OrderRepository orderRepository;


    private final PayPalHttpClient payPalHttpClient;


    public PayPalOrderCreateResponseDto startCheckOut(Long orderId) throws Exception
    {
        log.info("Payment service start check out");

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourcesNotFound("Order not found"));

        PayPalOrderCreateRequestDto payPalOrderCreateRequestDto = PayPalOrderMapper.mapToPayPalOrderCreateRequestDto(order);

        PayPalOrderCreateResponseDto payPalOrderCreateResponseDto = payPalHttpClient.createOrder(payPalOrderCreateRequestDto);

        order.setPaypalId(payPalOrderCreateResponseDto.getOrderId());

        return payPalOrderCreateResponseDto;
    }


    public PayPalOrderCaptureResponseDto captureOrder(String orderId) throws Exception
    {
        log.info("Payment service start capture");
        PayPalOrderCaptureResponseDto payPalOrderCaptureResponseDto = payPalHttpClient.captureOrder(orderId);

        Order order = orderRepository.findByPaypalId(orderId).orElseThrow(() -> new ResourcesNotFound("Order not found"));

        if (payPalOrderCaptureResponseDto.getStatus().contentEquals("COMPLETED"))
        {
            order.setOrderStatus(OrderStatusEnum.SUCCESSFUL);
            order.getPayment().setPaymentTimeStamp(LocalDateTime.now());
            order.getPayment().setPaymentStatusEnum(PaymentStatusEnum.COMPLETED);
        }
        else
        {
            order.setOrderStatus(OrderStatusEnum.CANCELLED);
            order.getPayment().setPaymentTimeStamp(LocalDateTime.now());
            order.getPayment().setPaymentStatusEnum(PaymentStatusEnum.CANCELLED);
        }

        return payPalOrderCaptureResponseDto;
    }
}