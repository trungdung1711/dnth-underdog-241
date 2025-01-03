package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.OrderStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.PaymentStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ShippingStatusEnum;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderGetAllResponseDto
{
    private Long id;

    private String message;

    private LocalDateTime timeStamp;

    private OrderStatusEnum orderStatus;

    private String shippingAddress;

    private ShippingStatusEnum shippingStatus;

    private Double totalPrice;

    private PaymentStatusEnum paymentStatus;
}
