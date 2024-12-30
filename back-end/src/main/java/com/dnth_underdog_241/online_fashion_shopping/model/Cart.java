package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartProduct> cartProducts = new ArrayList<>();


    @OneToOne(mappedBy = "cart")
    private Customer customer;


    public Double getTotalPrice()
    {
        return cartProducts
                .stream()
                .mapToDouble(cartProduct -> cartProduct.getPrice() * cartProduct.getQuantity())
                .sum();
    }
}