package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;

import java.util.HashSet;


public class RoleFactory
{
    public static Role createRoleAdmin()
    {
        return Role
                .builder()
                .name("ROLE_ADMIN")
                .webUsers(new HashSet<WebUser>())
                .build();
    }


    public static Role createRoleCustomer()
    {
        return Role
                .builder()
                .name("ROLE_CUSTOMER")
                .webUsers(new HashSet<WebUser>())
                .build();
    }


    public static Role createRoleEmployee()
    {
        return Role
                .builder()
                .name("ROLE_EMPLOYEE")
                .webUsers(new HashSet<WebUser>())
                .build();
    }
}
