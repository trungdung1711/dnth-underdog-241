package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
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
}