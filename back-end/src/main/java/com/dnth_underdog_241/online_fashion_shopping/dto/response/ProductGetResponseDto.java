package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.Material;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetResponseDto
{
    private Long id;
    private Double price;
    private Material material;
    private String shortDescription;
    private String longDescription;
    private String name;
    private String thumbnail;
    private String video;
    @Builder.Default
    private List<String> pictures = new ArrayList<>();
    private String brand;
    private String category;
}
