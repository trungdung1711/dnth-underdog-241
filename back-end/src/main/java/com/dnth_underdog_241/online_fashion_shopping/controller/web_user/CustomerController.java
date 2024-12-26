package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;

@Controller
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
   // Lấy danh sách khách hàng
   @GetMapping("/index")
   public ResponseEntity<List<Customer>> getAllCustomers() {
      List<Customer> customers = List.of(); // Replace với logic lấy danh sách khách hàng từ service hoặc repository

      return ResponseEntity.ok(customers);
   }
}
