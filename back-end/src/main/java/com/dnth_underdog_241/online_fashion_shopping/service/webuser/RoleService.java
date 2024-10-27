package com.dnth_underdog_241.online_fashion_shopping.service.webuser;


import com.dnth_underdog_241.online_fashion_shopping.model.enums.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.users.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleService
{
    private final RoleRepository roleRepository;


    private final WebUserRepository webUserRepository;


    @Transactional
    public void addRoleToWebUser(String phoneNumber, RoleEnum roleEnum)
    {
        /* Basic implementation*/
        Optional<WebUser> webUserOptional = webUserRepository.findByPhoneNumber(phoneNumber);
        Optional<Role> roleOptional = roleRepository.findByName(roleEnum);

        WebUser webUser = webUserOptional.get();
        Role role = roleOptional.get();

        webUser.getRoles().add(role);
    }
}
