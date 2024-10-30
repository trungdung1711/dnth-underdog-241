package com.dnth_underdog_241.online_fashion_shopping.service.webuser;


import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WebUserService
{
    private final WebUserRepository webUserRepository;


    public Optional<WebUser> getWebUser(String phoneNumber)
    {
        /* Basic Service */
        return webUserRepository.findByPhoneNumber(phoneNumber);
    }
};
