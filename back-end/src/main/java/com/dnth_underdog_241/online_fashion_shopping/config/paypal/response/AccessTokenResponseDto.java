package com.dnth_underdog_241.online_fashion_shopping.config.paypal.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenResponseDto
{
    private String scope;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("app_id")
    private String applicationId;
    @JsonProperty("expires_in")
    private int expiresIn;
    private String nonce;
    @JsonIgnore
    private Instant expiration;
}
