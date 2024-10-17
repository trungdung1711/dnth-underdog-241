package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.model.enums.Sex;

import java.time.LocalDate;
import java.util.HashSet;


public class WebUserFactory
{
    public static WebUser createUserA()
    {
        return WebUser
                .builder()
                .phoneNumber("0846979772")
                .password("Trungdung1711")
                .sex(Sex.MALE)
                .lastName("Le")
                .firstName("Dung")
                .email("trungdung1711@gmail.com")
                .birthDay(LocalDate.of(2004, 11, 17))
                .roles(new HashSet<Role>())
                .build();
    }
}
