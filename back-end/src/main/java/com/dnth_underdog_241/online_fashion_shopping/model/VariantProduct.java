package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "stock", nullable = false)
    private Long stock;


    @Column(name = "picture", nullable = false)
    private String picture;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "size", nullable = false)
    private Size size;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "colour", nullable = false)
    private Colour colour;


    @OneToMany(mappedBy = "variantProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<>();
}