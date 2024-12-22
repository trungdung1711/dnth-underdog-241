package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.WebUserGetDTO;
import com.dnth_underdog_241.online_fashion_shopping.dto.request.WebUserUpdateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.WebUserMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class WebUserService
{
    private final WebUserRepository webUserRepository;


    private final WebUserMapper webUserMapper;


    public WebUserGetDTO getWebUser(Long id)
    {
        WebUser webUser = webUserRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return webUserMapper.toDto(webUser);
    }


    public WebUserUpdateRequestDto updateWebUser(WebUserUpdateRequestDto webUserUpdateWebUserRequestDto, Long id)
    {
        /*
        * Load the WebUser object from the database (managed)
        * Map from the DTO to the Entity (any changes will be marked dirty
        * */
        WebUser webUser = webUserRepository
                .findById(id)
                .orElseThrow( () -> new UserNotFoundException(id) );

        webUserMapper.toEntity(webUserUpdateWebUserRequestDto, webUser);

        return webUserUpdateWebUserRequestDto;
    }


    public void deleteWebUser(Long id)
    {
        /* Found user, but admin can't delete admin */
        /*
        As Address is set as cascadeType = REMOVE
         */
        webUserRepository.deleteById(id);
    }
};
