package com.dnth_underdog_241.online_fashion_shopping.model.systemenum;


import java.util.Random;


public enum SupplierEnum
{
    ZARA("Fashion Brand"),
    H_AND_M("Fashion Brand"),
    MANGO("Fashion Brand"),
    UNIQLO("Fashion Brand"),
    LEVIS("Denim and Casual Wear"),
    GAP("Casual Wear"),
    TOMMY_HILFIGER("High-End Fashion"),
    CALVIN_KLEIN("Luxury and Casual Fashion"),
    ADIDAS("Sportswear"),
    NIKE("Sportswear"),
    SHEIN("Fast Fashion"),
    ALIBABA("Wholesale Distributor"),
    FASHION_TIY("Wholesale Distributor"),
    TASHA_APPAREL("Wholesale Distributor"),
    BRANDS_GATEWAY("Luxury Wholesale Distributor"),
    TENTREE("Sustainable Fashion"),
    EVERLANE("Sustainable Fashion"),
    PATAGONIA("Outdoor Wear"),
    ALTERNATIVE_APPAREL("Sustainable Everyday Wear"),
    BOODY("Eco-Friendly Basics"),
    PRINTFUL("Print-On-Demand"),
    PRINTIFY("Print-On-Demand"),
    MODALYST("Dropshipping"),
    SPOCKET("Dropshipping"),
    CJ_DROPSHIPPING("Dropshipping");


    private final String category;


    SupplierEnum(String category)
    {
        this.category = category;
    }


    public String getCategory()
    {
        return category;
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