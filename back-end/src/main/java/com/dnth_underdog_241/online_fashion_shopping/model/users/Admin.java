package com.dnth_underdog_241.online_fashion_shopping.model.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends WebUser
{
}
