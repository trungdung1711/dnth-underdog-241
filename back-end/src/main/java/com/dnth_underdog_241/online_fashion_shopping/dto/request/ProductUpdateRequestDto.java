package com.dnth_underdog_241.online_fashion_shopping.dto.request;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.Material;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequestDto
{
    private Double price;

    private Material material;

    private String shortDescription;

    private String longDescription;

    private String name;
}
