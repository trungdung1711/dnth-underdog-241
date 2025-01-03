package com.dnth_underdog_241.online_fashion_shopping.exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * Handler for filters
 * when an authenticated user
 * attempts to access a resource
 * they don’t have permission for
 * Return 403 Forbidden
 */
@Component
public class SecurityAccessDeniedHandler
implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
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
