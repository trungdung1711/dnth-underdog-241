package com.dnth_underdog_241.online_fashion_shopping.util;

import java.util.Random;


public class ImportCostCalculatorUtil
{
    private static final Random RANDOM = new Random();

    /**
     * Generates a random tax rate between 5% and 20%.
     *
     * @return the random tax rate as a percentage.
     */
    public static double generateRandomTaxRate()
    {
        return 5 + (15 * RANDOM.nextDouble());
    }

    /**
     * Generates a random shipping cost between $50 and $500.
     *
     * @return the random shipping cost.
     */
    public static double generateRandomShippingCost()
    {
        return 50 + (450 * RANDOM.nextDouble());
    }

    /**
     * Generates a random import tax between 2% and 10%.
     *
     * @return the random import tax rate as a percentage.
     */
    public static double generateRandomImportTaxRate()
    {
        return 2 + (8 * RANDOM.nextDouble());
    }

    /**
     * Calculates the total cost for importing a batch of products.
     *
     * @param unitCost     the cost of a single unit of the product.
     * @param quantity     the number of units being imported.
     * @return the total cost including shipping and taxes.
     */
    public static double calculateTotalCost(Double unitCost, Long quantity)
    {
        double productCost = unitCost * quantity;
        double taxRate = generateRandomTaxRate();
        double shippingCost = generateRandomShippingCost();
        double importTaxRate = generateRandomImportTaxRate();

        double tax = (productCost * taxRate) / 100;
        double importTax = (productCost * importTaxRate) / 100;

        return productCost + shippingCost + tax + importTax;
    }
}