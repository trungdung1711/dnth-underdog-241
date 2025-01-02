package com.dnth_underdog_241.online_fashion_shopping.config.paypal.paypalenum;

public enum PayPalEndPointEnum
{
    GET_ACCESS_TOKEN("/v1/oauth2/token"),
    GET_CLIENT_TOKEN("/v1/identity/generate-token"),
    ORDER_CHECKOUT("/v2/checkout/orders"),
    ORDER_CAPTURE("v2/checkout/orders/{orderId}/capture"),;


    private final String path;


    PayPalEndPointEnum(String path)
    {
        this.path = path;
    }

    public static String createUrl(String baseUrl, PayPalEndPointEnum endpoint)
    {
        return baseUrl + endpoint.path;
    }


    public static String createCaptureUrl(String baseUrl, String orderId)
    {
        return baseUrl + "/v2/checkout/orders/" + orderId + "/capture";
    }


    public static String createUrl(String baseUrl, PayPalEndPointEnum endpoint, String... params)
    {
        return baseUrl + String.format(endpoint.path, (Object) params);
    }
}