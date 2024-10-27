package com.dnth_underdog_241.online_fashion_shopping.util.objectfactory;


import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser;


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


    public static Address createAddressB()
    {
        return Address
                .builder()
                .province("Lam Dong")
                .city("Da Lat")
                .ward("Ward 8")
                .street("213 Mai Hac De")
                .build();
    }


    public static Address createAddressC()
    {
        return Address
                .builder()
                .province("Thanh pho Ho Chi Minh")
                .city("Thanh pho Thu Duc")
                .ward("Ward 2")
                .street("KTX khu B DHQG")
                .build();
    }
}
