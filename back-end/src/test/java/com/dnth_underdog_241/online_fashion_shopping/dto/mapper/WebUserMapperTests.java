package com.dnth_underdog_241.online_fashion_shopping.dto.mapper;


import com.dnth_underdog_241.online_fashion_shopping.config.TestsConfiguration;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.WebUserResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.model.users.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.util.DataInitializer;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.WebUserFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestsConfiguration.class)
public class WebUserMapperTests
{
    @Autowired
    private WebUserMapper webUserMapper;


    @Test
    public void haveWebUserRequestDto_WithWebUserMapper_ReturnCorrectEntity()
    {
        WebUser webUser = WebUserFactory.createUserA();

        WebUserRequestDto dto = new WebUserRequestDto(
                webUser.getPhoneNumber(),
                webUser.getPassword(),
                webUser.getLastName(),
                webUser.getFirstName());

        WebUser entity = webUserMapper.toEntity(dto);

        Assertions
                .assertThat(entity.getPhoneNumber())
                .isEqualTo(dto.getPhoneNumber());
        Assertions
                .assertThat(entity.getPassword())
                .isEqualTo(dto.getPassword());
        Assertions
                .assertThat(entity.getLastName())
                .isEqualTo(dto.getLastName());
        Assertions
                .assertThat(entity.getFirstName())
                .isEqualTo(dto.getFirstName());
    }


    @Test
    public void haveWebUserEntity_WithWebUserMapper_ReturnCorrectResponseDto()
    {
        WebUser entity  = WebUserFactory.createUserA();

        WebUserResponseDto dto = webUserMapper.toDto(entity);

        Assertions
                .assertThat(dto.getPhoneNumber())
                .isEqualTo(entity.getPhoneNumber());

        Assertions
                .assertThat(dto.getLastName())
                .isEqualTo(entity.getLastName());

        Assertions
                .assertThat(dto.getFirstName())
                .isEqualTo(entity.getFirstName());
    }
}
