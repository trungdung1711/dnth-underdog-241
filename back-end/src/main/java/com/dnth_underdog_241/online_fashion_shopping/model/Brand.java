package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand")
public class Brand
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @Column(name = "url", nullable = false)
    private String url;
}