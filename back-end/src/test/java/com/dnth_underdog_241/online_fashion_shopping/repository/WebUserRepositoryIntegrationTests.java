package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.config.TestsConfiguration;
import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.util.DataInitialiser;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.WebUserFactory;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@Import(TestsConfiguration.class)
public class WebUserRepositoryIntegrationTests
{
    @Autowired
    WebUserRepository webUserRepository;


    @Autowired
    RoleRepository roleRepository;


    @Autowired
    DataInitialiser dataInitialiser;


    @BeforeEach
    public void setUp()
    {
        dataInitialiser.setUp();
    }


    @Test
    public void getWebUser_WithPhoneNumber_ReturnsCorrectWebUser()
    {
        WebUser webUserA = WebUserFactory.createUserA();
        Optional<WebUser> webUserOptional = webUserRepository.findByPhoneNumber(webUserA.getPhoneNumber());
        Assertions
                .assertThat(webUserOptional)
                .isPresent();
        Assertions
                .assertThat(webUserOptional.get().getPhoneNumber())
                .isEqualTo(webUserA.getPhoneNumber());
        Assertions
                .assertThat(webUserOptional.get().getPassword())
                .isEqualTo(webUserA.getPassword());
    }


    @Test
    public void getWebUser_WithPhoneNumber_ReturnsWebUserWithRoles()
    {
        WebUser webUserA = WebUserFactory.createUserA();
        Optional<WebUser> webUserOptional = webUserRepository.findByPhoneNumber(webUserA.getPhoneNumber());

        Assertions
                .assertThat(webUserOptional)
                .isPresent();
        Assertions
                .assertThat(webUserOptional.get().getRoles().size())
                .isEqualTo(1);
    }
};
