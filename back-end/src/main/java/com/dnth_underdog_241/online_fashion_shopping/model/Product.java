package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.Material;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "price", nullable = false)
    private Double price;


    @Enumerated(EnumType.STRING)
    @Column(name = "material", nullable = false)
    private Material material;


    @Column(name = "short_description")
    private String shortDescription;


    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;


    @Column(name = "video")
    private String video;


    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Picture> pictures = new ArrayList<>();


    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();


    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VariantProduct> variantProducts = new ArrayList<>();


    @Transient
    public List<Size> getSizes()
    {
        return variantProducts
                .stream()
                .map(VariantProduct::getSize)
                .distinct()
                .collect(Collectors.toList());
    }


    @Transient
    public List<Colour> getColours()
    {
        return variantProducts
                .stream()
                .map(VariantProduct::getColour)
                .distinct()
                .collect(Collectors.toList());
    }


    @Transient
    public Long getTotalStock()
    {
        return variantProducts
                .stream()
                .mapToLong(VariantProduct::getStock)
                .sum();
    }
}