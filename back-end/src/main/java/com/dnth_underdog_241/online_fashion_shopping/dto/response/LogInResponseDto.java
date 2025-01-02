package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import lombok.Value;

import java.util.Set;


@Value
public class LogInResponseDto
{
    Long id;
    String jwttoken;
    String phoneNumber;
    Set<Role> roles;
}
