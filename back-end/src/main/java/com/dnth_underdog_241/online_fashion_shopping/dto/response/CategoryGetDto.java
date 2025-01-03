package com.dnth_underdog_241.online_fashion_shopping.dto.response;

import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.CategoryEnum;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.Category}
 */
@Value
public class CategoryGetDto implements Serializable {
    Long id;
    String name;
    CategoryEnum categoryEnum;
}