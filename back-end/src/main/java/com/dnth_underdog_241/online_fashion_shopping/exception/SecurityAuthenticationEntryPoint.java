package com.dnth_underdog_241.online_fashion_shopping.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom AuthenticationEntryPoint implementation to handle unauthorized access attempts.
 * It sends a JSON response with an error message, timestamp, and status code.
 * This handler is triggered whenever an unauthenticated user tries to access a protected resource.
 */
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException
    {
        // Set the response status to 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Create a structured error response as a JSON object
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now().toString());
        errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorDetails.put("error", "Unauthorized");
        errorDetails.put("message", "Access denied. Please authenticate to access this resource.");
        errorDetails.put("path", request.getRequestURI());

        // Convert the errorDetails map to a JSON string
        String jsonResponse = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(errorDetails);

        // Write the JSON response body
        response.getWriter().write(jsonResponse);
    }
}
