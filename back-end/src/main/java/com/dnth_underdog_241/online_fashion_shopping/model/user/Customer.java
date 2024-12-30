package com.dnth_underdog_241.online_fashion_shopping.model.user;


import com.dnth_underdog_241.online_fashion_shopping.model.Cart;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends WebUser
{
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}