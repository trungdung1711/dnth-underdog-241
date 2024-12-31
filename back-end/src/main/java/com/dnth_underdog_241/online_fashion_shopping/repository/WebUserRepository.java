package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WebUserRepository extends JpaRepository<WebUser, Long>
{
    Optional<WebUser> findByPhoneNumber(String phoneNumber);

    List<WebUser> findByRoles(Role role);


    boolean existsByPhoneNumber(String phoneNumber);


    void deleteWebUserByPhoneNumber(String phoneNumber);
}
