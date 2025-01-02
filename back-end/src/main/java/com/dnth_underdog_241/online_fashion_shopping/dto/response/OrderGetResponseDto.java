package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderGetResponseDto
{
    private Long id;

    private String message;

    private OrderStatusEnum orderStatus;

    private LocalDateTime timeStamp;

    private ShippingStatusEnum shippingStatus;

    private LocalDate expectedDate;

    private ShipmentAgencyEnum shipmentAgency;

    private String shippingAddress;

    private Double amount;

    private PaymentMethodEnum paymentMethod;

    private PaymentStatusEnum paymentStatus;

    private LocalDateTime paymentTimeStamp;

    private List<OrderItemGetResponseDto> orderItems;
}
