package com.dnth_underdog_241.online_fashion_shopping.util;


import com.dnth_underdog_241.online_fashion_shopping.model.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import com.dnth_underdog_241.online_fashion_shopping.repository.RoleRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.RoleFactory;
import com.dnth_underdog_241.online_fashion_shopping.util.objectfactory.WebUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;


@TestComponent
public class DataInitialiser
{
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private WebUserRepository webUserRepository;


    public void setUp()
    {
        Role adminRole = RoleFactory.createRoleAdmin();
        Role customerRole = RoleFactory.createRoleCustomer();
        Role employeeRole = RoleFactory.createRoleEmployee();

        roleRepository.save(adminRole);
        roleRepository.save(customerRole);
        roleRepository.save(employeeRole);

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").get();
        WebUser userA = WebUserFactory.createUserA();

        userA.getRoles().add(roleAdmin);
        roleAdmin.getWebUsers().add(userA);
        webUserRepository.save(userA);
    }
}
