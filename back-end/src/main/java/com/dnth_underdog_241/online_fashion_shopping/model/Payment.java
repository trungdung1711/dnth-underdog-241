package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.PaymentMethodEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "amount", nullable = false)
    private Double amount;


    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method_enum")
    private PaymentMethodEnum paymentMethodEnum;


    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status_enum", nullable = false)
    private PaymentStatusEnum paymentStatusEnum;


    @Column(name = "payment_time_stamp")
    private LocalDateTime paymentTimeStamp;
}