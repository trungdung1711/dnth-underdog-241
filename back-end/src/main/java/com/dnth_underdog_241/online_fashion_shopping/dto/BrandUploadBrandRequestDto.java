package com.dnth_underdog_241.online_fashion_shopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dnth_underdog_241.online_fashion_shopping.model.Brand}
 */
@Value
public class BrandUploadBrandRequestDto implements Serializable {
    @NotNull(message = "Name should be specified")
    @NotBlank(message = "Name should be specified")
    String name;
    String dummy;
}