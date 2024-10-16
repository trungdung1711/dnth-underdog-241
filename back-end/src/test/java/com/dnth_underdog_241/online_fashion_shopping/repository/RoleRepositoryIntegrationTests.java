package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleRepositoryIntegrationTests
{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private WebUserRepository webUserRepository;


    @Test
    @Transactional
    public void getRoles_WithName_ReturnsWebUsers()
    {
        WebUser webUser = WebUser
                .builder()
                .passWord("password")
                .phoneNumber("0846979772")
                .build();
        Optional<Role> role1 = roleRepository.findByName("ROLE_CUSTOMER");
        Set<Role> roles = new HashSet<>();
        Set<WebUser> webUsers = new HashSet<>();
        roles.add(role1.get());
        webUsers.add(webUser);
        webUser.setRoles(roles);
        role1.get().setWebUsers(webUsers);
        webUserRepository.save(webUser);

        Optional<Role> foundRole = roleRepository.findByName("ROLE_CUSTOMER");
        Assertions.assertThat(foundRole.isPresent()).isTrue();
        Assertions.assertThat(foundRole.get().getName()).isEqualTo("ROLE_CUSTOMER");
        Assertions.assertThat(foundRole.get().getWebUsers().size()).isEqualTo(1);
    }
};
