package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import org.assertj.core.api.Assertions;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WebUserRepositoryIntegrationTests
{
    @Autowired
    WebUserRepository webUserRepository;
    @Autowired
    RoleRepository roleRepository;


    @Test
    public void save_WithNoId_ReturnsWebUser()
    {
        WebUser webUser = WebUser
                .builder()
                .passWord("password")
                .phoneNumber("0846979772")
                .build();
        webUserRepository.save(webUser);
        Optional<WebUser> webUserOptional = webUserRepository.findById(webUser.getId());
        Assertions.assertThat(webUserOptional).isPresent();
        Assertions.assertThat(webUserOptional.get().getPhoneNumber()).isEqualTo(webUser.getPhoneNumber());
        Assertions.assertThat(webUserOptional.get().getPassWord()).isEqualTo(webUser.getPassWord());
    }


    @Test
    public void saveWebUser_WithRoles_ReturnsWebUserWithRoles()
    {
        WebUser webUser = WebUser
                .builder()
                .passWord("password")
                .phoneNumber("0846979772")
                .build();
        Optional<Role> role1 = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role1.get());
        webUser.setRoles(roles);
        webUserRepository.save(webUser);
        Optional<WebUser> webUserOptional = webUserRepository.findById(webUser.getId());

        Assertions
                .assertThat(webUserOptional)
                .isPresent();
        Assertions
                .assertThat(webUserOptional.get().getRoles())
                .isEqualTo(roles);
        Assertions
                .assertThat(webUserOptional.get().getRoles().size())
                .isEqualTo(1);
    }
};
