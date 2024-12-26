package com.dnth_underdog_241.online_fashion_shopping.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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

   @RequestMapping("/update-info")
   public String updateInfo() {
      return "update-info";
   }

   @GetMapping("/logout")
   public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
      SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
      logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
      request.getSession().invalidate();  // Hủy session
      return new RedirectView("/index");  // Điều hướng về trang /index sau khi logout
   }

}
