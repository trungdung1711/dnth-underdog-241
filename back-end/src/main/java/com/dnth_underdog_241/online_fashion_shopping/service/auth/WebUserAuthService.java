package com.dnth_underdog_241.online_fashion_shopping.service.auth;

import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserAlreadyExistsException;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WebUserAuthService
{
    private final WebUserRepository webUserRepository;


    private final RoleRepository roleRepository;


    private final WebUserMapper webUserMapper;


    private final PasswordEncoder passwordEncoder;


    @Transactional
    public WebUserResponseDto signIn(WebUserRequestDto webUserRequestDto)
    {
        if (webUserRepository.existsByPhoneNumber(webUserRequestDto.getPhoneNumber()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        WebUser webUser = webUserMapper.toEntity(webUserRequestDto);
        webUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        Role roleCustomer = roleRepository.findByName(RoleEnum.ROLE_CUSTOMER).get();
        webUser.getRoles().add(roleCustomer);

        WebUser savedWebUser = webUserRepository.save(webUser);
        return webUserMapper.toDto(savedWebUser);
    }
}
