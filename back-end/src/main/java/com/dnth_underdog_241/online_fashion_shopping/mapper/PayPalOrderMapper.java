package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.config.paypal.request.PayPalOrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import com.dnth_underdog_241.online_fashion_shopping.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PayPalOrderMapper
{
    public static PayPalOrderCreateRequestDto mapToPayPalOrderCreateRequestDto(Order order)
    {
        // Map OrderItems to PurchaseUnits
        List<PayPalOrderCreateRequestDto.PurchaseUnit> purchaseUnits = List.of(
                PayPalOrderCreateRequestDto.PurchaseUnit.builder()
                        .invoiceId(order.getId().toString())
                        .amount(mapAmount(order))
                        .items(order.getOrderItems().stream()
                                .map(PayPalOrderMapper::mapOrderItemToItem)
                                .toList()
                        )
                        .build()
        );


        // Map the DTO
        return PayPalOrderCreateRequestDto.builder()
                .intent("CAPTURE") // Typically "CAPTURE" or "AUTHORIZE"
                .purchaseUnits(purchaseUnits)
                .paymentSource(mapPaymentSource())
                .build();
    }


    private static PayPalOrderCreateRequestDto.Amount mapAmount(Order order)
    {
        // Calculate total price
        double totalValue = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Map breakdown if needed
        PayPalOrderCreateRequestDto.AmountBreakdown breakdown = PayPalOrderCreateRequestDto.AmountBreakdown.builder()
                .itemTotal(PayPalOrderCreateRequestDto.Money.builder()
                        .currencyCode("USD")
                        .value(String.format("%.2f", totalValue))
                        .build())
                .build();

        return PayPalOrderCreateRequestDto.Amount.builder()
                .currencyCode("USD")
                .value(String.format("%.2f", totalValue))
                .breakdown(breakdown)
                .build();
    }


    private static PayPalOrderCreateRequestDto.Item mapOrderItemToItem(OrderItem orderItem)
    {
        return PayPalOrderCreateRequestDto.Item.builder()
                .name(orderItem.getName())
                .description(orderItem.getShortDescription()) // Add a proper description if available
                .unitAmount(PayPalOrderCreateRequestDto.Money.builder()
                        .currencyCode("USD")
                        .value(String.format("%.2f", orderItem.getPrice()))
                        .build())
                .quantity(String.valueOf(orderItem.getQuantity()))
                .category("PHYSICAL_GOODS") // Adjust based on your item category
                .sku(orderItem.getSku()) // Assuming Product has an ID
                .imageUrl("https://i.ibb.co/Vp8Jn9Q/2-AA94-E01-8386-4402-8669-1524-A7-EB75-B9.png")
                .build();
    }


    private static PayPalOrderCreateRequestDto.PaymentSource mapPaymentSource()
    {
        return PayPalOrderCreateRequestDto.PaymentSource.builder()
                .paypal(PayPalOrderCreateRequestDto.PayPalDetails.builder()
                        .experienceContext(PayPalOrderCreateRequestDto.ExperienceContext.builder()
                                .paymentMethodPreference("IMMEDIATE_PAYMENT_REQUIRED")
                                .landingPage("LOGIN")
                                .shippingPreference("NO_SHIPPING")
                                .userAction("PAY_NOW")
                                .returnUrl("http://localhost:8080/api/v1/payment/orders/capture") // Replace with actual URL
                                .cancelUrl("https://example.com/cancel") // Replace with actual URL
                                .build())
                        .build())
                .build();
    }
}