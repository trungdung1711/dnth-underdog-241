package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WebUserRepository extends JpaRepository<WebUser, Long>
{
};