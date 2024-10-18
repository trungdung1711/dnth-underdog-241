package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.config.TestsConfiguration;
import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.util.DataInitialiser;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.AddressFactory;
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

import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@Import(TestsConfiguration.class)
public class AddressRepositoryIntegrationTests
{
    @Autowired
    private AddressRepository addressRepository;


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
    public void getWebUser_GetAddress_ReturnTrueUpdatedAddress()
    {
        /* For getting the phone number only */
        WebUser webUserA = WebUserFactory.createUserA();
        Address addressA = AddressFactory.createAddressA();

        Optional<WebUser> webUserOptional = webUserRepository.findByPhoneNumber(webUserA.getPhoneNumber());

        Assertions.assertThat(webUserOptional).isPresent();
        Assertions.assertThat(webUserOptional.get().getAddress().getProvince()).isEqualTo(addressA.getProvince());
        Assertions.assertThat(webUserOptional.get().getAddress().getCity()).isEqualTo(addressA.getCity());
        Assertions.assertThat(webUserOptional.get().getAddress().getWard()).isEqualTo(addressA.getWard());
        Assertions.assertThat(webUserOptional.get().getAddress().getStreet()).isEqualTo(addressA.getStreet());
    }
}
