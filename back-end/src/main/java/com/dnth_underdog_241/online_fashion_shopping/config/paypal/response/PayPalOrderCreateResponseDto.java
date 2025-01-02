package com.dnth_underdog_241.online_fashion_shopping.config.paypal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayPalOrderCreateResponseDto
{
    @JsonProperty("id")
    private String orderId;


    @JsonProperty("status")
    private String status;


    @JsonProperty("links")
    private List<Link> links;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Link
    {
        @JsonProperty("href")
        private String href;

        @JsonProperty("rel")
        private String rel;

        @JsonProperty("method")
        private String method;
    }
}
