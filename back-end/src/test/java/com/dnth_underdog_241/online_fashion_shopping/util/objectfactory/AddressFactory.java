package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.model.Address;


public class AddressFactory
{
    public static Address createAddressA()
    {
        return Address
                .builder()
                .province("Lam Dong")
                .city("Da Lat")
                .ward("Ward 7")
                .street("2/7 Nguyen Hoang")
                .build();
    }
}
