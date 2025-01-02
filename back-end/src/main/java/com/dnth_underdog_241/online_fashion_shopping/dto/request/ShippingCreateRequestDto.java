package com.dnth_underdog_241.online_fashion_shopping.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingCreateRequestDto
{
    @NotNull(message = "Province required")
    private String province;
    @NotNull(message = "City required")
    private String city;
    @NotNull(message = "Ward required")
    private String ward;
    @NotNull(message = "Street required")
    private String street;
}
