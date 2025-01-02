package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProductGetResponseDto
{
    private Long id;

    private Long productId;

    private Long variantProductId;

    private String name;

    private String thumbnail;

    private String shortDescription;

    private Double price;

    private Long quantity;

    private SizeEnum size;

    private ColourEnum colour;

    private LocalDateTime timeStamp;
}
