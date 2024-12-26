package com.dnth_underdog_241.online_fashion_shopping.model;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "colour")
public class Colour
{
    @Id
    @Column(name = "colour", nullable = false)
    @Enumerated(EnumType.STRING)
    private ColourEnum colour;
}