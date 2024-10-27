package com.dnth_underdog_241.online_fashion_shopping.security.service;


import com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.security.model.WebUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WebUserDetailsService implements UserDetailsService
{
    private final WebUserRepository webUserRepository;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber)
            throws UsernameNotFoundException
    {
        Optional<WebUser> webUserOptional = webUserRepository.findByPhoneNumber(phoneNumber);
        WebUser webUser = webUserOptional.orElseThrow(() -> new UsernameNotFoundException(phoneNumber));

        return new WebUserDetails(webUser);
    }
}
