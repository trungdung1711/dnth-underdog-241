package com.dnth_underdog_241.online_fashion_shopping.dto.request;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.PaymentMethodEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ShipmentAgencyEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequestDto
{
    private List<Long> cartProductIdList;

    @NotNull(message = "Shipping address not provided")
    private Long shippingAddressId;

    @NotNull(message = "Shipping agency not provided")
    private ShipmentAgencyEnum shipmentAgency;

    @NotNull(message = "Payment method not provided")
    private PaymentMethodEnum paymentMethod;

    private String message;
}