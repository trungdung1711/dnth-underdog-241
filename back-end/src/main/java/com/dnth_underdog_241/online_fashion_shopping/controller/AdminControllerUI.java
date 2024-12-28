package com.dnth_underdog_241.online_fashion_shopping.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControllerUI {

    @GetMapping
    public String index() {
        return "Admin/index";
    }

    @GetMapping("/product")
    public String products() {
        return "Admin/products";
    }

    @GetMapping("/order")
    public String orders() {
        return "Admin/orders";
    }

    @GetMapping("/comment")
    public String comments() {
        return "Admin/comments";
    }

    @GetMapping("/manager")
    public String manager() {
        return "Admin/manager";
    }

    @GetMapping("/customer")
    public String customer() {
        return "Admin/customers";
    }

    @GetMapping("/infor")
    public String infor() {
        return "Admin/infoAdmin";
    }

}
