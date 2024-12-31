package com.dnth_underdog_241.online_fashion_shopping.service.customer;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.CustomerGetAllRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserAlreadyExistsException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.SignUpMapper;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Cart;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.CustomerRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService
{
    private final WebUserRepository webUserRepository;


    private final RoleRepository roleRepository;


    private final SignUpMapper signUpMapper;


    private final PasswordEncoder passwordEncoder;


    private final CustomerRepository customerRepository;


    private final WebUserMapper webUserMapper;


    @Transactional
    public SignUpResponseDto createCustomer(SignUpRequestDto signUpRequestDto)
    {
        if (webUserRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        Customer webUser = signUpMapper.toCustomerEntity(signUpRequestDto);
        webUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        Role roleCustomer = roleRepository.findByName(RoleEnum.ROLE_CUSTOMER).orElseThrow(() -> new ResourcesNotFound("Role Customer Not Found"));
        webUser.getRoles().add(roleCustomer);

        webUser.setCart
                (
                        Cart
                                .builder()
                                .cartProducts(new ArrayList<>())
                                .customer(webUser)
                                .build()
                );

        webUser.setAddresses(new ArrayList<>());
        webUser.setOrders(new ArrayList<>());

        WebUser savedWebUser = webUserRepository.save(webUser);
        return signUpMapper.toDto(savedWebUser);
    }


    public Page<CustomerGetAllRequestDto> getAllCustomers(Pageable pageable)
    {
        return customerRepository.getAllCustomers(pageable).map(webUserMapper::toDto1);
    }
}