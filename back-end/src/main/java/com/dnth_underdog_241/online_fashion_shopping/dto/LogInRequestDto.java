package com.dnth_underdog_241.online_fashion_shopping.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class LogInRequestDto
{
    String phoneNumber;
    String password;
}
