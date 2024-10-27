package com.dnth_underdog_241.online_fashion_shopping.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser}
 */
@Value
public class WebUserRequestDto implements Serializable
{
    String phoneNumber;
    String password;
    String lastName;
    String firstName;
}