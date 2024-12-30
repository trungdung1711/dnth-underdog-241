package com.dnth_underdog_241.online_fashion_shopping.mapper.impl;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.ShippingAddressGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.AddressMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import org.springframework.stereotype.Component;


@Component
public class AddressMapperImpl implements AddressMapper
{

    @Override
    public ShippingAddressGetResponseDto toShippingAddressGetResponseDto(Address address)
    {
        return ShippingAddressGetResponseDto
                .builder()
                .id(address.getId())
                .address
                        (
                                address.getProvince() + ", " +
                                address.getCity() + ", " +
                                address.getWard() + ", " +
                                address.getStreet())
                .build();
    }
}
