package com.dnth_underdog_241.online_fashion_shopping.config.paypal;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
@Setter
public class PaypalConfiguration
{
    @NotNull
    @Value("${paypal.baseUrl}")
    private String baseUrl;


    @NotNull
    @Value("${paypal.clientId}")
    private String clientId;


    @NotNull
    @Value("${paypal.secret}")
    private String clientSecret;
}
