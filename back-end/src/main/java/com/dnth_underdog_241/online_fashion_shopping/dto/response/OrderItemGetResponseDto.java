package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import lombok.*;


@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemGetResponseDto
{
    private Long id;

    private String name;

    private Double price;

    private Long quantity;

    private SizeEnum size;

    private ColourEnum colour;
}
