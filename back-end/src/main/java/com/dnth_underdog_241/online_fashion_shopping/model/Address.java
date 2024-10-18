package com.dnth_underdog_241.online_fashion_shopping.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @Column(nullable = false)
    private String province;


    @Column(nullable = false)
    private String city;


    @Column(nullable = false)
    private String ward;


    @Column(nullable = false)
    private String street;

}
