package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);


    Page<Order> findAll(Pageable pageable);
    

    Optional<Order> findByPaypalId(String orderId);

    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.timeStamp >= :startDate AND o.timeStamp <= :endDate")
    List<Order> findOrdersByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.orderStatus = :orderStatus")
    List<Order> findOrdersByStatus(OrderStatusEnum orderStatus);
}