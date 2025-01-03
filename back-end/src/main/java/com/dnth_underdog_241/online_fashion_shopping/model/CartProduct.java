package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_product_id", nullable = false)
    private VariantProduct variantProduct;


    @Column(name = "price", nullable = false)
    private Double price;


    @Column(name = "quantity", nullable = false)
    private Long quantity;


    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;
}