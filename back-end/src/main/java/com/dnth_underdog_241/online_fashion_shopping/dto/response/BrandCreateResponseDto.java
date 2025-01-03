package com.dnth_underdog_241.online_fashion_shopping.dto.response;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.Brand}
 */
@Value
public class BrandCreateResponseDto implements Serializable {
    Long id;
    String name;
    String url;
}