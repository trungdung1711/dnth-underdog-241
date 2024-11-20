package com.dnth_underdog_241.online_fashion_shopping.controller.web_user.customer;


import com.dnth_underdog_241.online_fashion_shopping.dto.GetWebUserResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController
{
}