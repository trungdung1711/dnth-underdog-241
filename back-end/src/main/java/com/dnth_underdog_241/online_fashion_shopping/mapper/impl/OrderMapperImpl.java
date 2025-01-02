package com.dnth_underdog_241.online_fashion_shopping.mapper.impl;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.OrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderItemGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.OrderItemMapper;
import com.dnth_underdog_241.online_fashion_shopping.mapper.OrderMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.*;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.OrderStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.PaymentStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ShippingStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.repository.AddressRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.CartProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.util.WebUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper
{
    private final AddressRepository addressRepository;
    private final CartProductRepository cartProductRepository;
    private final WebUserRepository webUserRepository;
    private final WebUserUtil webUserUtil;
    private final OrderItemMapper orderItemMapper;


    @Override
    public Order toOrder(OrderCreateRequestDto orderCreateRequestDto)
    {
        Order order = new Order();


        Shipment shipment = Shipment
                .builder()
                .shippingStatusEnum(ShippingStatusEnum.PROCESSING)
                .date(LocalDate.now())
                .expectedDate(LocalDate.now().plusDays(14))
                .shipmentAgencyEnum(orderCreateRequestDto.getShipmentAgency())
                .address(addressRepository.findById(orderCreateRequestDto.getShippingAddressId()).orElseThrow(() -> new RuntimeException("Address Not Found")))
                .build();


        List<OrderItem> orderItemList =
        orderCreateRequestDto
                .getCartProductIdList()
                .stream()
                .map
                        (
                                id ->
                                {
                                    CartProduct cartProduct = cartProductRepository
                                            .findById(id).orElseThrow(() -> new ResourcesNotFound("Cart Product Not Found"));

                                    return OrderItem
                                            .builder()
                                            .name(cartProduct.getVariantProduct().getProduct().getName())
                                            .price(cartProduct.getPrice() * cartProduct.getQuantity())
                                            .quantity(cartProduct.getQuantity())
                                            .sizeEnum(cartProduct.getVariantProduct().getSize().getSize())
                                            .colourEnum(cartProduct.getVariantProduct().getColour().getColour())
                                            .shortDescription(cartProduct.getVariantProduct().getProduct().getShortDescription())
                                            .sku("OFS_" + cartProduct.getId())
                                            .order(order)
                                            .build();
                                }
                        )
                .toList();

        order.setOrderItems(orderItemList);


        Payment payment = Payment
                .builder()
                .amount
                        (
                                orderItemList
                                        .stream()
                                        .mapToDouble(OrderItem::getPrice)
                                        .sum()
                        )
                .paymentMethodEnum(orderCreateRequestDto.getPaymentMethod())
                .paymentStatusEnum(PaymentStatusEnum.PENDING)
                .paymentTimeStamp(null)
                .build();

        Customer customer = (Customer) webUserRepository
                .findById
                        (
                                webUserUtil.getAuthenticatedUserId()
                        )
                        .orElseThrow(() -> new UserNotFoundException(webUserUtil.getAuthenticatedUserId()));


        order.setMessage(orderCreateRequestDto.getMessage());
        order.setOrderStatus(OrderStatusEnum.PENDING);
        order.setShipment(shipment);
        order.setPayment(payment);
        order.setOrderItems(orderItemList);
        order.setCustomer(customer);

        return order;
    }


    @Override
    public OrderCreateResponseDto toOrderCreateResponseDto(Order order)
    {
        return OrderCreateResponseDto
                .builder()
                .id(order.getId())
                .message(order.getMessage())
                .totalItem
                        (
                                order
                                        .getOrderItems()
                                        .stream()
                                        .mapToLong(OrderItem::getQuantity)
                                        .sum()
                        )
                .totalPrice(order.getPayment().getAmount())
                .shippingAddress
                        (
                                order.getShipment().getAddress().getStreet() + ", " +
                                        order.getShipment().getAddress().getWard() + ", " +
                                        order.getShipment().getAddress().getCity() +", " +
                                        order.getShipment().getAddress().getProvince()
                        )
                .orderStatus(order.getOrderStatus())
                .paymentMethod(order.getPayment().getPaymentMethodEnum())
                .paymentStatus(order.getPayment().getPaymentStatusEnum())
                .shipmentAgency(order.getShipment().getShipmentAgencyEnum())
                .shippingStatus(order.getShipment().getShippingStatusEnum())
                .build();
    }


    @Override
    public OrderGetAllResponseDto toOrderGetAllResponseDto(Order order)
    {
        return OrderGetAllResponseDto
                .builder()
                .id(order.getId())
                .message(order.getMessage())
                .timeStamp(order.getTimeStamp())
                .orderStatus(order.getOrderStatus())
                .shippingAddress
                        (
                                order.getShipment().getAddress().getStreet() + ", " +
                                        order.getShipment().getAddress().getWard() + ", " +
                                        order.getShipment().getAddress().getCity() + ", " +
                                        order.getShipment().getAddress().getProvince()
                        )
                .shippingStatus(order.getShipment().getShippingStatusEnum())
                .totalPrice(order.getPayment().getAmount())
                .paymentStatus(order.getPayment().getPaymentStatusEnum())
                .build();
    }


    @Override
    public OrderGetResponseDto toOrderGetResponseDto(Order order)
    {
        List<OrderItem> orderItemList = order.getOrderItems();

        List<OrderItemGetResponseDto> orderItemGetResponseDtoList =
                orderItemList
                        .stream()
                        .map(orderItemMapper::toOrderItemGetResponseDto)
                        .toList();

        return OrderGetResponseDto
                .builder()
                .id(order.getId())
                .message(order.getMessage())
                .orderStatus(order.getOrderStatus())
                .timeStamp(order.getTimeStamp())
                .shippingStatus(order.getShipment().getShippingStatusEnum())
                .expectedDate(order.getShipment().getExpectedDate())
                .shipmentAgency(order.getShipment().getShipmentAgencyEnum())
                .shippingAddress
                        (
                                order.getShipment().getAddress().getStreet()+", "+
                                        order.getShipment().getAddress().getWard()+", "+
                                        order.getShipment().getAddress().getCity()+", "+
                                        order.getShipment().getAddress().getProvince()
                        )
                .amount(order.getPayment().getAmount())
                .paymentMethod(order.getPayment().getPaymentMethodEnum())
                .paymentStatus(order.getPayment().getPaymentStatusEnum())
                .paymentTimeStamp(order.getPayment().getPaymentTimeStamp())
                .orderItems(orderItemGetResponseDtoList)
                .build();
    }
}