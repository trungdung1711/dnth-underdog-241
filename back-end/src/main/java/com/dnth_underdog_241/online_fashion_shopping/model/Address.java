package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "province", nullable = false)
    private String province;


    @Column(name = "city", nullable = false)
    private String city;


    @Column(name = "ward", nullable = false)
    private String ward;


    @Column(name = "street", nullable = false)
    private String street;
}