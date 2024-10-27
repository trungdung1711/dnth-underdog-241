package com.dnth_underdog_241.online_fashion_shopping.dto.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.users.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface WebUserMapper
{
    Customer toEntity(WebUserRequestDto webUserRequestDto);


    WebUserResponseDto toDto(WebUser webUser);
}
