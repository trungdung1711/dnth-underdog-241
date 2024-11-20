package com.dnth_underdog_241.online_fashion_shopping.service.webuser;


import com.dnth_underdog_241.online_fashion_shopping.dto.GetWebUserResponseDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.UpdateWebUserDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class WebUserService
{
    private final WebUserRepository webUserRepository;


    private final WebUserMapper webUserMapper;


    public GetWebUserResponseDTO getWebUser(Long id)
    {
        WebUser webUser = webUserRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return webUserMapper.toDto(webUser);
    }


    public UpdateWebUserDto updateWebUser(UpdateWebUserDto updateWebUserDto, Long id)
    {
        /*
        * Load the WebUser object from the database (managed)
        * Map from the DTO to the Entity (any changes will be marked dirty
        * */
        WebUser webUser = webUserRepository
                .findById(id)
                .orElseThrow( () -> new UserNotFoundException(id) );

        webUserMapper.toEntity(updateWebUserDto, webUser);

        return updateWebUserDto;
    }
};
