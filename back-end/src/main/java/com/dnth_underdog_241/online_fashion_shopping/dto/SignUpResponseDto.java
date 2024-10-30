package com.dnth_underdog_241.online_fashion_shopping.dto;

import lombok.Value;

import java.io.Serializable;


@Value
public class SignUpResponseDto implements Serializable
{
    String phoneNumber;
    String lastName;
    String firstName;
}