package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.Material;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetAllResponseDto
{
    private Long id;

    private Double price;

    private Material material;

    private String shortDescription;

    private String name;

    private String thumbnail;

    private String brand;

    private String category;
}