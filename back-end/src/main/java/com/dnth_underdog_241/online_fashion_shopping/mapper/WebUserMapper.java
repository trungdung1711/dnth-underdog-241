package com.dnth_underdog_241.online_fashion_shopping.mapper;

import com.dnth_underdog_241.online_fashion_shopping.dto.GetWebUserResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserUpdateWebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface WebUserMapper
{
    void toEntity(WebUserUpdateWebUserRequestDto webUserUpdateWebUserRequestDto, @MappingTarget WebUser webUser);


    GetWebUserResponseDTO toDto(WebUser webUser);
}
