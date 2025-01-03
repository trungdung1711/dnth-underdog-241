package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.ImportTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ImportTransactionRepository extends JpaRepository<ImportTransaction, Long>
{
    @Query("SELECT it " +
            "FROM ImportTransaction it " +
            "WHERE it.date >= :startDate AND it.date <= :endDate")
    List<ImportTransaction> findTransactionsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}