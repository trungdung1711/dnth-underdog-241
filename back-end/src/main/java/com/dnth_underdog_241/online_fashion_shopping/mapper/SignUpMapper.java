package com.dnth_underdog_241.online_fashion_shopping.mapper;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Admin;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Employee;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SignUpMapper
{
    Customer toCustomerEntity(SignUpRequestDto signUpRequestDto);


    Employee toEmployeeEntity(SignUpRequestDto signUpRequestDto);


    Admin toAdminEntity(SignUpRequestDto signUpRequestDto);


    SignUpResponseDto toDto(WebUser webUser);
}
