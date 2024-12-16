package com.dnth_underdog_241.online_fashion_shopping.dto;


import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import lombok.Value;

import java.util.Set;


@Value
public class LogInResponseDto
{
    Long id;
    String JWTToken;
    String phoneNumber;
    Set<Role> roles;
}
