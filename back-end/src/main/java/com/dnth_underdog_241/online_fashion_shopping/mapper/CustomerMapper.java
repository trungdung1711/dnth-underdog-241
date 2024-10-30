package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.CustomerResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper
{
    CustomerResponseDTO toDto(WebUser webUser);
}
