package com.dnth_underdog_241.online_fashion_shopping.service.customer;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.SignUpRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.SignUpResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserAlreadyExistsException;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CustomerMapper;
import com.dnth_underdog_241.online_fashion_shopping.mapper.SignUpMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Cart;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService
{
    private final WebUserRepository webUserRepository;


    private final RoleRepository roleRepository;


    private final CustomerMapper customerMapper;


    private final SignUpMapper signUpMapper;


    private final PasswordEncoder passwordEncoder;


    public WebUserGetDTO getCustomerById(Long id)
    {
        Optional<WebUser> webUserOptional = webUserRepository.findById(id);

        WebUser webUser = webUserOptional.orElseThrow( () -> new UserNotFoundException(id));

        if (! (webUser instanceof Customer) )
            throw new UserNotFoundException(id);

        return customerMapper.toDto(webUser);
    }


    @Transactional
    public SignUpResponseDto createCustomer(SignUpRequestDto signUpRequestDto)
    {
        if (webUserRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        Customer webUser = signUpMapper.toCustomerEntity(signUpRequestDto);
        webUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        Role roleCustomer = roleRepository.findByName(RoleEnum.ROLE_CUSTOMER).get();
        webUser.getRoles().add(roleCustomer);

        webUser.setCart
                (
                        Cart
                                .builder()
                                .cartProducts(new ArrayList<>())
                                .customer(webUser)
                                .build()
                );

        WebUser savedWebUser = webUserRepository.save(webUser);
        return signUpMapper.toDto(savedWebUser);
    }
}