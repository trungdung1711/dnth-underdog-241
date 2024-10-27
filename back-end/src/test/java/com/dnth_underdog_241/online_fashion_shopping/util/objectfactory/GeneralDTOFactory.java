package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserResponseDto;

public class GeneralDTOFactory
{
    public static WebUserRequestDto createWebUserRequestDtoA()
    {
        return new WebUserRequestDto
                (
                        "0846979772",
                        "Trungdung1711",
                        "Le",
                        "Dung"
                );
    }


    public static WebUserResponseDto createWebUserResponseDtoA()
    {
        return new WebUserResponseDto
                (
                        "0846979772",
                        "Le",
                        "Dung"
                );
    }
}
