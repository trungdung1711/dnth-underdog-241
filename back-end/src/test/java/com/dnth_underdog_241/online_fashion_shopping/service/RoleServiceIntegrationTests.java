package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.config.TestsConfiguration;
import com.dnth_underdog_241.online_fashion_shopping.service.webuser.RoleService;
import com.dnth_underdog_241.online_fashion_shopping.util.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestsConfiguration.class)
@Transactional
public class RoleServiceIntegrationTests
{
    @Autowired
    private RoleService roleService;


    @Autowired
    private WebUserService webUserService;


    @Autowired
    private DataInitializer dataInitializer;


    @BeforeEach
    public void setUp()
    {
        dataInitializer.setUp();
    }


    @Test
    public void addRole_whenValidRole_thenReturnsWebUserWithNewRoles()
    {
        /* For comparison*/
//        WebUser webUserA = WebUserFactory.createUserA();
//
//        roleService.addRoleToWebUser(webUserA.getPhoneNumber(), RoleEnum.ROLE_EMPLOYEE);
//
//        //WebUser updatedWebUserA = webUserService.getWebUser(webUserA.getId()).get();
//
//        Assertions
//                .assertThat(updatedWebUserA.getRoles())
//                .hasSize(2);
    }
}
