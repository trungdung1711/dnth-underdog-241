package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SupplierEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportTransaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "status", nullable = false)
    private String status;


    @Enumerated(EnumType.STRING)
    @Column(name = "supplier_enum", nullable = false)
    private SupplierEnum supplierEnum;


    @Column(name = "shipping_cost", nullable = false)
    private Double shippingCost;


    @Column(name = "unit_cost", nullable = false)
    private String unitCost;


    @Column(name = "tax", nullable = false)
    private Double tax;


    @Column(name = "quantity", nullable = false)
    private Long quantity;


    @Column(name = "total_cost", nullable = false)
    private Double totalCost;
}