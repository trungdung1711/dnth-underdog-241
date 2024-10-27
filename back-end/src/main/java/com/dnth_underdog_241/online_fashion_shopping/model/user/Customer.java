package com.dnth_underdog_241.online_fashion_shopping.model.user;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends WebUser
{
}
