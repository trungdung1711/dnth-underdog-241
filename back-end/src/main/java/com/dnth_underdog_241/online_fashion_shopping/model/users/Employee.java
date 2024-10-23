package com.dnth_underdog_241.online_fashion_shopping.model.users;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@AllArgsConstructor
@Builder
@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends WebUser
{
}
