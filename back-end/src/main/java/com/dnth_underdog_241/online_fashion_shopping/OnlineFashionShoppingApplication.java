package com.dnth_underdog_241.online_fashion_shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {"com.dnth_underdog_241.online_fashion_shopping"})
@EnableScheduling
public class OnlineFashionShoppingApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(OnlineFashionShoppingApplication.class, args);
	}
}
