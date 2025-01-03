package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import lombok.Value;

import java.time.LocalDate;


@Value
public class WebUserGetDTO
{
    Long id;
    String phoneNumber;
    SexEnum sex;
    String lastName;
    String firstName;
    String email;
    LocalDate birthDay;
    AddressGetDto address;
}
