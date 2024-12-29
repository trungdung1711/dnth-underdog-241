package com.dnth_underdog_241.online_fashion_shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

   @RequestMapping("/index")
   public String index() {
      return "index";
   }

   @RequestMapping("/home")
   public String home() {
      return "home";
   }

   @RequestMapping("/contact")
   public String contact() {
      return "contact";
   }

   @RequestMapping("/about")
   public String about() {
      return "about";
   }

   @RequestMapping("/login")
   public String login() {
      return "login";
   }

   @RequestMapping("/signup")
   public String signup() {
      return "signup";
   }

   @RequestMapping("/shop")
   public String shop() {
      return "shop";
   }

   @RequestMapping("/cart")
   public String cart() {
      return "shopping-cart";
   }

   @RequestMapping("/details")
   public String details() {
      return "shop-details";
   }

}
