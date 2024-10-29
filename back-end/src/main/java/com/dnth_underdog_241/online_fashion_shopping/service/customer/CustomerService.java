package com.dnth_underdog_241.online_fashion_shopping.service.customer;


import com.dnth_underdog_241.online_fashion_shopping.dto.CustomerResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CustomerMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService
{
    private final WebUserRepository webUserRepository;


    private final CustomerMapper customerMapper;


    public CustomerResponseDTO getCustomerById(Long id)
    {
        Optional<WebUser> webUserOptional = webUserRepository.findById(id);
        WebUser webUser = webUserOptional.orElseThrow( () -> new UserNotFoundException(id));

        return customerMapper.toDto(webUser);
    }
}
