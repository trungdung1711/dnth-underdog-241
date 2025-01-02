package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ShipmentAgencyEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ShippingStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_status_enum", nullable = false)
    private ShippingStatusEnum shippingStatusEnum;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "expected_date", nullable = false)
    private LocalDate expectedDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "shipment_agency_enum", nullable = false)
    private ShipmentAgencyEnum shipmentAgencyEnum;


    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
