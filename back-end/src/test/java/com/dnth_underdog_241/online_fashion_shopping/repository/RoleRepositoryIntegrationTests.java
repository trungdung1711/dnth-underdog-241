package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.config.TestsConfiguration;
import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.util.DataInitialiser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(value = {TestsConfiguration.class})
@Transactional
public class RoleRepositoryIntegrationTests
{
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private WebUserRepository webUserRepository;


    @Autowired
    private DataInitialiser dataInitialiser;


    @BeforeEach
    public void setUp()
    {
        dataInitialiser.setUp();
    }


    @Test
    public void getRole_WithName_ReturnsCorrectRole()
    {
        Optional<Role> foundRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Assertions.assertThat(foundRoleAdmin.isPresent()).isTrue();
        Assertions.assertThat(foundRoleAdmin.get().getName()).isEqualTo("ROLE_ADMIN");
        Assertions.assertThat(foundRoleAdmin.get().getWebUsers().size()).isEqualTo(1);
    }
};
