package com.dnth_underdog_241.online_fashion_shopping.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.Brand}
 */
@Value
public class BrandUploadBrandResponseDto implements Serializable {
    Long id;
    String name;
    String url;
}