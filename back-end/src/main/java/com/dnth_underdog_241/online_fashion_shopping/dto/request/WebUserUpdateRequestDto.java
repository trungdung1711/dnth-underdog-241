package com.dnth_underdog_241.online_fashion_shopping.dto.request;

import com.dnth_underdog_241.online_fashion_shopping.dto.response.AddressGetDto;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser}
 */
@Value
public class WebUserUpdateRequestDto implements Serializable
{
    SexEnum sex;
    String lastName;
    String firstName;
    String email;
    LocalDate birthDay;
    AddressGetDto address;
}