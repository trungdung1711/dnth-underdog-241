package com.dnth_underdog_241.online_fashion_shopping.dto.response;

import lombok.Value;

import java.io.Serializable;


@Value
public class SignUpResponseDto implements Serializable
{
    Long id;
    String phoneNumber;
    String lastName;
    String firstName;
}