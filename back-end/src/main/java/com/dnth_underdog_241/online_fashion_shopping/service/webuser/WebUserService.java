package com.dnth_underdog_241.online_fashion_shopping.service.webuser;


import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
