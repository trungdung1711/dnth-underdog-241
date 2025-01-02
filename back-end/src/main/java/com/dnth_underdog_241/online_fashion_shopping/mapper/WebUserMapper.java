package com.dnth_underdog_241.online_fashion_shopping.mapper;

import com.dnth_underdog_241.online_fashion_shopping.dto.response.CustomerGetAllRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.EmployeeGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.WebUserUpdateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface WebUserMapper
{
    void toEntity(WebUserUpdateRequestDto webUserUpdateWebUserRequestDto, @MappingTarget WebUser webUser);

    WebUserGetDTO toDto(WebUser webUser);

    //WebUser toEntity(CustomerGetAllRequestDto customerGetAllRequestDto);

    CustomerGetAllRequestDto toDto1(WebUser webUser);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //WebUser partialUpdate(CustomerGetAllRequestDto customerGetAllRequestDto, @MappingTarget WebUser webUser);

    //WebUser toEntity(EmployeeGetAllResponseDto employeeGetAllResponseDto);

    EmployeeGetAllResponseDto toDto2(WebUser webUser);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //WebUser partialUpdate(EmployeeGetAllResponseDto employeeGetAllResponseDto, @MappingTarget WebUser webUser);
}
