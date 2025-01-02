package com.dnth_underdog_241.online_fashion_shopping.dto.response;


import lombok.*;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ShippingAddressGetResponseDto
{
    private Long id;
    private String address;
}
