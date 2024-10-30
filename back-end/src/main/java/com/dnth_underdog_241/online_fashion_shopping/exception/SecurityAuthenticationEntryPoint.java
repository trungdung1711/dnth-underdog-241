package com.dnth_underdog_241.online_fashion_shopping.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Handler for filters when
 * invoked when an unauthenticated user
 * tries to access a protected resource.
 *
 */
@Component
public class SecurityAuthenticationEntryPoint
    implements AuthenticationEntryPoint
{
    @Value("${com.dnth_underdog_241.online_fashion_shopping.server.name}")
    private String serverName;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response
                .getWriter()
                .write("{\"" + serverName + "AuthenticationEntryPoint\": \"Unauthorized access\"}");
    }
}
