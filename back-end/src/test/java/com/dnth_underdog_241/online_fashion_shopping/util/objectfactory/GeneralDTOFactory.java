package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.SignUpResponseDto;

public class GeneralDTOFactory
{
    public static SignUpRequestDto createWebUserRequestDtoA()
    {
        return new SignUpRequestDto
                (
                        "0846979772",
                        "Trungdung1711",
                        "Le",
                        "Dung"
                );
    }


    public static SignUpResponseDto createWebUserResponseDtoA()
    {
        return new SignUpResponseDto
                (
                        "0846979772",
                        "Le",
                        "Dung"
                );
    }
}
