package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "price", nullable = false)
    private Double price;


    @Column(name = "quantity", nullable = false)
    private Long quantity;


    @Column(name = "short_description", nullable = false)
    private String shortDescription;


    @Column(name = "sku", nullable = false)
    private String sku = "OFS_";


    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private SizeEnum sizeEnum;


    @Enumerated(EnumType.STRING)
    @Column(name = "colour_enum", nullable = false)
    private ColourEnum colourEnum;


    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
