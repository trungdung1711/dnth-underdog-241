package com.dnth_underdog_241.online_fashion_shopping.dto;


import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import lombok.Value;

import java.time.LocalDate;


@Value
public class GetWebUserResponseDTO
{
    Long id;
    String phoneNumber;
    SexEnum sex;
    String lastName;
    String firstName;
    String email;
    LocalDate birthDay;
    AddressDto address;
}
