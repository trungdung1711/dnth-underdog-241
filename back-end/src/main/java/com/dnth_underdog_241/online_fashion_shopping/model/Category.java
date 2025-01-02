package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CategoryEnum categoryEnum;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;


    @OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Category> children = new ArrayList<>();


    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}