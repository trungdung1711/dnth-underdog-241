package com.dnth_underdog_241.online_fashion_shopping.dto;


import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import lombok.Value;

import java.util.Set;


@Value
public class LogInResponseDto
{
    String JWTToken;
    String phoneNumber;
    Set<Role> roles;
}
