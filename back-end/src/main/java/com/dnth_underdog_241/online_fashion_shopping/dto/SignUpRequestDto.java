package com.dnth_underdog_241.online_fashion_shopping.dto;

import lombok.Value;

import java.io.Serializable;


@Value
public class SignUpRequestDto implements Serializable
{
    String phoneNumber;
    String password;
    String lastName;
    String firstName;
}