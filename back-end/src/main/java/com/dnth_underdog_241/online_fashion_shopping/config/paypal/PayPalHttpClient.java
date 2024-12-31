package com.dnth_underdog_241.online_fashion_shopping.config.paypal;


import com.dnth_underdog_241.online_fashion_shopping.config.paypal.request.PayPalOrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.AccessTokenResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.PayPalOrderCaptureResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.config.paypal.response.PayPalOrderCreateResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import static com.dnth_underdog_241.online_fashion_shopping.config.paypal.paypalenum.PayPalEndPointEnum.*;


@Slf4j
@Component
public class PayPalHttpClient
{
    private final HttpClient httpClient;


    private final PaypalConfiguration paypalConfiguration;


    private final ObjectMapper objectMapper;


    @Autowired
    public PayPalHttpClient(PaypalConfiguration paypalConfiguration, ObjectMapper objectMapper)
    {
        this.paypalConfiguration = paypalConfiguration;
        this.objectMapper = objectMapper;
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }


    public AccessTokenResponseDto getAccessToken() throws Exception
    {
        // Step 1: Base64 encode client ID and secret
        String clientId = paypalConfiguration.getClientId();
        String clientSecret = paypalConfiguration.getClientSecret();

        String basicAuth = Base64
                .getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes());


        // Step 2: Create HTTP client
        HttpClient httpClient = HttpClient.newHttpClient();


        // Step 3: Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUrl(paypalConfiguration.getBaseUrl(), GET_ACCESS_TOKEN)))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en_US")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        // Step 4: Send request
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        // Step 5: Check response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get access token: " + response.body());
        }


        // Step 6: Parse response
        String content = response.body();
        ObjectMapper objectMapper = new ObjectMapper();


        return objectMapper.readValue(content, AccessTokenResponseDto.class);
    }


    public PayPalOrderCreateResponseDto createOrder(PayPalOrderCreateRequestDto payPalOrderCreateRequestDto) throws Exception
    {
        log.info("RestAPI to PayPal gateway createOrder");

        // Get the access token for authorization
        AccessTokenResponseDto accessTokenDto = getAccessToken();

        // Serialize the request object to JSON
        String payload = objectMapper.writeValueAsString(payPalOrderCreateRequestDto);

        // Create the HTTP request with appropriate headers and body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUrl(paypalConfiguration.getBaseUrl(), ORDER_CHECKOUT)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenDto.getAccessToken())
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response content as a string
        String content = response.body();

        // Deserialize the response content to an OrderCreateResponseDto object

        log.info("Returned from Paypal gateway [Order content]: {}", content);
        return objectMapper.readValue(content, PayPalOrderCreateResponseDto.class);
    }


    public PayPalOrderCaptureResponseDto captureOrder(String orderId) throws Exception
    {
        log.info("RestAPI to PayPal gateway captureOrder");

        // Get the access token for authorization
        AccessTokenResponseDto accessTokenDto = getAccessToken();

        // Build the URL for capturing the order
        String captureUrl = createCaptureUrl(paypalConfiguration.getBaseUrl(), orderId);

        // Create the HTTP request with the appropriate headers
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(captureUrl))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenDto.getAccessToken())
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response content as a string
        String content = response.body();

        // Check if the response status is successful
        if (response.statusCode() != 201)
        { // 201 Created is the expected status code for a successful capture
            throw new RuntimeException("Failed to capture order: " + content);
        }
        log.info("Returned from Paypal gateway [Capture content]: {}", content);

        // Deserialize the response content into a PayPalCaptureResponseDto object
        return objectMapper.readValue(content, PayPalOrderCaptureResponseDto.class);
    }
}