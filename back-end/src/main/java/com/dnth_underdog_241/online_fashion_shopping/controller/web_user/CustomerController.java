package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;

import com.dnth_underdog_241.online_fashion_shopping.dto.request.ShippingCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ShippingAddressGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CustomerMapper;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;

@Controller
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController
{
   private final WebUserRepository webUserRepository;

   private  final CustomerMapper customerMapper;

   private  final WebUserMapper webUserMapper;
   private final AddressService addressService;


   @GetMapping("/index")
   public ResponseEntity<List<WebUserGetDTO>> getAllCustomers()
   {
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


   @PostMapping("{id}/shippings")
   @PreAuthorize("hasRole('CUSTOMER')")
   public ResponseEntity<Void> addShippingAddress(@RequestBody @Valid ShippingCreateRequestDto shippingCreateRequestDto, @PathVariable Long id)
   {
      addressService.addShipping(shippingCreateRequestDto, id);

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(null);
   }


   @GetMapping("{id}/shippings")
   @PreAuthorize("hasRole('CUSTOMER')")
   public ResponseEntity<List<ShippingAddressGetResponseDto>> getShippingAddresses(@PathVariable Long id)
   {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body
                      (
                              addressService.getShippingAddress(id)
                      );
   }


   @DeleteMapping("{id}/shippings/{addressId}")
   @PreAuthorize("hasRole('CUSTOMER')")
   public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id, @PathVariable Long addressId)
   {
      addressService.deleteShippingAddress(id,addressId);

      return ResponseEntity
              .status(HttpStatus.NO_CONTENT)
              .body(null);
   }
}