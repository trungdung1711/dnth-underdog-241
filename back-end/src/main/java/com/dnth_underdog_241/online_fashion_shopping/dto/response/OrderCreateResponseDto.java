package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.*;
import lombok.*;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateResponseDto
{
    private Long id;

    private String message;

    private Long totalItem;

    private Double totalPrice;

    private String shippingAddress;

    private OrderStatusEnum orderStatus;

    private PaymentMethodEnum paymentMethod;

    private PaymentStatusEnum paymentStatus;

    private ShipmentAgencyEnum shipmentAgency;

    private ShippingStatusEnum shippingStatus;
}