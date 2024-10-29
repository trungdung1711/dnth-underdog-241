package com.dnth_underdog_241.online_fashion_shopping.controller.auth;


import com.dnth_underdog_241.online_fashion_shopping.config.TestsConfiguration;
import com.dnth_underdog_241.online_fashion_shopping.dto.LogInRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.GeneralDTOFactory;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.WebUserFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestsConfiguration.class)
@Transactional
public class AuthControllerIntegrationTests
{
    @Autowired
    private MockMvc mvc;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void signInNewWebUser_WithValidPhoneNumber_ResponseCreated()
            throws Exception
    {
        String request = objectMapper
                .writeValueAsString(GeneralDTOFactory.createWebUserRequestDtoA());
        String response = objectMapper
                .writeValueAsString(GeneralDTOFactory.createWebUserResponseDtoA());
        mvc
                .perform
                        (
                                MockMvcRequestBuilders
                                        .post("/api/v1/auth/sign-up")
                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                                        .content(request)
                        )
                .andExpect
                        (
                                MockMvcResultMatchers
                                        .content()
                                        .json(response)
                        );
    }
}
