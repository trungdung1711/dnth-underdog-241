package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SexEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Admin;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Employee;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;

import java.time.LocalDate;


public class WebUserFactory
{
    public static WebUser createUserA()
    {
        Customer customerA = new Customer();
        customerA.setPhoneNumber("0846979772");
        customerA.setPassword("Trungdung1711");
        customerA.setSex(SexEnum.MALE);
        customerA.setLastName("Le");
        customerA.setFirstName("Dung");
        customerA.setEmail("trungdung1711@gmail.com");
        customerA.setBirthDay(LocalDate.of(2004, 11, 17));
        return customerA;
    }


    public static WebUser createUserB()
    {
        Employee employeeB = new Employee();
        employeeB.setPhoneNumber("01213828895");
        employeeB.setPassword("Password123");
        employeeB.setSex(SexEnum.OTHER);
        employeeB.setLastName("Maths");
        employeeB.setFirstName("John");
        employeeB.setEmail("join-smith12@gmail.com");
        employeeB.setBirthDay(LocalDate.of(2003, 12, 20));
        return employeeB;
    }


    public static WebUser createUserC()
    {
        Admin adminC = new Admin();
        adminC.setPhoneNumber("125874658");
        adminC.setPassword("Password123");
        adminC.setSex(SexEnum.FEMALE);
        adminC.setLastName("Hikari");
        adminC.setFirstName("Haiku");
        adminC.setEmail("hikari-haiku@gmail.com");
        adminC.setBirthDay(LocalDate.of(1998, 1, 14));
        return adminC;
    }
}
