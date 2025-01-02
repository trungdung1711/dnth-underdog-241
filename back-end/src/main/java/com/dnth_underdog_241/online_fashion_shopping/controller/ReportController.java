package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import com.dnth_underdog_241.online_fashion_shopping.model.Report;
import com.dnth_underdog_241.online_fashion_shopping.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reports")
@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
@RequiredArgsConstructor
public class ReportController
{
    private final ReportRepository reportRepository;


    @GetMapping
    public ResponseEntity<Page<Report>> getOrders(Pageable pageable)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reportRepository.findAll(pageable));
    }
}