package com.dnth_underdog_241.online_fashion_shopping.dto;

import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser}
 */
@Value
public class UpdateWebUserDto implements Serializable
{
    SexEnum sex;
    String lastName;
    String firstName;
    String email;
    LocalDate birthDay;
    AddressDto address;
}