package com.dnth_underdog_241.online_fashion_shopping.util;


import com.dnth_underdog_241.online_fashion_shopping.model.Address;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.RoleEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.AddressFactory;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.WebUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;


@TestComponent
public class DataInitializer
{
    @Autowired
    private WebUserRepository webUserRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public void setUp()
    {
        /* managed instance */
        Role roleCustomer = roleRepository.findByName(RoleEnum.ROLE_CUSTOMER).get();
        Role roleAdmin = roleRepository.findByName(RoleEnum.ROLE_ADMIN).get();
        Role roleEmployee = roleRepository.findByName(RoleEnum.ROLE_EMPLOYEE).get();

        WebUser webUserA = WebUserFactory.createUserA();
        Address addressA = AddressFactory.createAddressA();
        WebUser webUserB = WebUserFactory.createUserB();
        Address addressB = AddressFactory.createAddressB();
        WebUser webUserC = WebUserFactory.createUserC();
        Address addressC = AddressFactory.createAddressC();

        webUserA.getRoles().add(roleCustomer);

        webUserB.getRoles().add(roleAdmin);
        webUserB.getRoles().add(roleEmployee);
        webUserB.getRoles().add(roleCustomer);

        webUserC.getRoles().add(roleEmployee);
        webUserC.getRoles().add(roleCustomer);

        webUserA.setAddress(addressA);
        webUserB.setAddress(addressB);
        webUserC.setAddress(addressC);

        webUserRepository.save(webUserA);
        webUserRepository.save(webUserB);
        webUserRepository.save(webUserC);
    }
}
