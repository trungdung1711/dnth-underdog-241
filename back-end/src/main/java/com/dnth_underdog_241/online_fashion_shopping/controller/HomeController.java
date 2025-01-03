
package com.dnth_underdog_241.online_fashion_shopping.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @RequestMapping("/index")
   public String index() {
      return "index";
   }

   @GetMapping("/home")
   public String home() {
      return "Customer/home";
   }

   @RequestMapping("/contact")
   public String contact() {
      return "Customer/contact";
   }

   @RequestMapping("/about")
   public String about() {
      return "Customer/about";
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
      return "Customer/shop";
   }

   @RequestMapping("/checkout")
   public String checkout() {
      return "Customer/checkout";
   }

   @RequestMapping("/cart")
   public String cart() {
      return "Customer/shopping-cart";
   }

   @RequestMapping("/paybal")
   public String paybal() {
      return "pp";
   }

   @RequestMapping("/detail/{id}")
   public String details(@PathVariable Long id, Model model) {
      model.addAttribute("productId", id);
      return "Customer/shop-details";
   }

   @GetMapping("/update-info")
   public String UpdateInfo() {
      return "Customer/update-info";
   }


   @GetMapping("/logout")
   public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
      SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
      logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
      request.getSession().invalidate();
      return new RedirectView("/index");
   }

}
