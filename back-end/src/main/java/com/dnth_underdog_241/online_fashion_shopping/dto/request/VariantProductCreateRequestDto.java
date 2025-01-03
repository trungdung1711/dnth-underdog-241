package com.dnth_underdog_241.online_fashion_shopping.dto.request;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VariantProductCreateRequestDto
{
    private Long id;
    private Long stock;
    private SizeEnum size;
    private ColourEnum colour;
}