package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.OrderStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "message")
    private String message;


    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatusEnum orderStatus = OrderStatusEnum.PENDING;


    @Column(name = "time_stamp")
    private LocalDateTime timeStamp = LocalDateTime.now();


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false, orphanRemoval = true)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false, orphanRemoval = true)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;


    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}