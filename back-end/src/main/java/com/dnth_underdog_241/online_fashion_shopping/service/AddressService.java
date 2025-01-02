package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.ShippingCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.ShippingAddressGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.AddressMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.AddressRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressService
{
    private final AddressRepository addressRepository;


    private final WebUserRepository webUserRepository;
    private final AddressMapper addressMapper;


    public void addShipping(ShippingCreateRequestDto shippingCreateRequestDto, Long id)
    {
        WebUser webUser = webUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Customer customer = (Customer) webUser;

        Address shippingAddress = Address
                .builder()
                .province(shippingCreateRequestDto.getProvince())
                .city(shippingCreateRequestDto.getCity())
                .ward(shippingCreateRequestDto.getWard())
                .street(shippingCreateRequestDto.getStreet())
                .customer(customer)
                .build();

        addressRepository.save(shippingAddress);

        customer.getAddresses().add(shippingAddress);
    }


    public List<ShippingAddressGetResponseDto> getShippingAddress(Long id)
    {
        Customer customer = (Customer) webUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return
                customer
                        .getAddresses()
                        .stream()
                        .map(addressMapper::toShippingAddressGetResponseDto)
                        .collect(Collectors.toList());
    }


    public void deleteShippingAddress(Long id, Long addressId)
    {
        addressRepository.deleteById(addressId);
    }
}