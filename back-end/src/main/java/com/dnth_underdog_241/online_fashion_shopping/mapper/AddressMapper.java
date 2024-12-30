package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.ShippingAddressGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import org.springframework.stereotype.Component;


@Component
public interface AddressMapper
{
    ShippingAddressGetResponseDto toShippingAddressGetResponseDto(Address address);
}
