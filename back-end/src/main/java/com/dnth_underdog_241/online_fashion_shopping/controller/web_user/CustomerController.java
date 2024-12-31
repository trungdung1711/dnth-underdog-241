package com.dnth_underdog_241.online_fashion_shopping.controller.web_user;

import com.dnth_underdog_241.online_fashion_shopping.dto.request.ShippingCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.CustomerGetAllRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ShippingAddressGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.service.AddressService;
import com.dnth_underdog_241.online_fashion_shopping.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController
{
   private final AddressService addressService;


   private final CustomerService customerService;


   @GetMapping
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<Page<CustomerGetAllRequestDto>> getAllCustomers(Pageable pageable)
   {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(customerService.getAllCustomers(pageable));
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