package com.dnth_underdog_241.online_fashion_shopping.dto.response;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.Address}
 */
@Value
public class AddressGetDto implements Serializable
{
    String province;
    String city;
    String ward;
    String street;
}