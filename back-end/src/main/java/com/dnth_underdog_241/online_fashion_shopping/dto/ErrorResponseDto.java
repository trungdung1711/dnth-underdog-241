package com.dnth_underdog_241.online_fashion_shopping.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ErrorResponseDto
{
    private Integer status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
