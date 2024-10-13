package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WebUserRepositoryIntegrationTests
{
    @Autowired
    WebUserRepository webUserRepository;


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
};
