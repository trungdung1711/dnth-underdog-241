package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;

import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CustomerMapper;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;

@Controller
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
   private final WebUserRepository webUserRepository;

   private  final CustomerMapper customerMapper;

   private  final WebUserMapper webUserMapper;

   @Autowired
   private RoleRepository roleRepository;

   // Lấy danh sách khách hàng
   @GetMapping("/index")
   public ResponseEntity<List<WebUserGetDTO>> getAllCustomers() {
      List<WebUser> brands = webUserRepository.findAll();
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(
                      brands
                              .stream()
                              .map(webUserMapper::toDto)
                              .collect(Collectors.toList())
              );
   }
   @GetMapping
   public ResponseEntity<List<WebUserGetDTO>> getAllCustomer()
   {
      Role roleEmployee = roleRepository
              .findByName(RoleEnum.ROLE_CUSTOMER)
              .get();
      List<WebUser> webUser = webUserRepository.findByRoles(roleEmployee);
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(
                      webUser
                              .stream()
                              .map(webUserMapper::toDto)
                              .collect(Collectors.toList())
              );
   }

}
