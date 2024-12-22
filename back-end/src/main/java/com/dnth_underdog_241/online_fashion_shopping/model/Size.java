package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Size
{
    @Id
    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;
}