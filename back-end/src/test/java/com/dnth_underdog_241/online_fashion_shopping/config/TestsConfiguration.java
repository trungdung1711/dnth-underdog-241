package com.dnth_underdog_241.online_fashion_shopping.config;


import com.dnth_underdog_241.online_fashion_shopping.util.DataInitializer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestsConfiguration
{
    @Bean
    public DataInitializer createDataInitializer()
    {
        return new DataInitializer();
    }
}
