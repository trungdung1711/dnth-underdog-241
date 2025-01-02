package com.dnth_underdog_241.online_fashion_shopping.model.systemenum;


import lombok.Getter;

import java.util.Random;


@Getter
public enum SupplierEnum
{
    ZARA("Zara"),
    H_AND_M("H&M"),
    MANGO("Mango"),
    UNIQLO("Uniqlo"),
    LEVIS("Levis"),
    GAP("Gap"),
    ADIDAS("Adidas"),
    NIKE("Nike"),
    SHEIN("Shein"),
    ALIBABA("Alibaba"),
    BOODY("Boody");


    private final String name;


    SupplierEnum(String name)
    {
        this.name = name;
    }


    /**
     * Static method to get a random supplier.
     *
     * @return a randomly selected Supplier.
     */
    public static SupplierEnum getRandomSupplier()
    {
        SupplierEnum[] suppliers = SupplierEnum.values();
        Random random = new Random();
        return suppliers[random.nextInt(suppliers.length)];
    }
}