package com.dnth_underdog_241.online_fashion_shopping.security.filter;

import com.dnth_underdog_241.online_fashion_shopping.exception.InvalidTokenException;
import com.dnth_underdog_241.online_fashion_shopping.security.service.WebUserDetailsService;
import com.dnth_underdog_241.online_fashion_shopping.security.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final WebUserDetailsService webUserDetailsService;
    private final JwtUtil jwtUtil;
    @Value("${com.dnth_underdog_241.online_fashion_shopping.server.name}")
    private String serverName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestPath = request.getRequestURI();

        // Bỏ qua các tài nguyên tĩnh
        if (requestPath.startsWith("/css/") ||
                requestPath.startsWith("/js/") ||
                requestPath.startsWith("/images/") ||
                requestPath.startsWith("/webjars/")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                if (jwtUtil.validateToken(token)) {
                    String subject = jwtUtil.extractAllClaims(token).getSubject();
                    UserDetails userDetails = webUserDetailsService.loadUserByUsername(subject);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (InvalidTokenException e) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT_error_Invalid-token-Exception",
                    e.getMessage());
            return;
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT_error_Expired-Exception",
                    "Token has expired");
            return;
        } catch (UnsupportedJwtException e) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT_error_Unsupported-Exception",
                    "Token is unsupported");
            return;
        } catch (MalformedJwtException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "JWT_error_Malformed-Exception",
                    "Token is malformed");
            return;
        } catch (SignatureException e) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT_error_Signature-Exception",
                    "Signature does not match");
            return;
        } catch (JwtException e) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT_error_Unknown-Exception",
                    "Token is invalid. Login redirect.");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String errorCode, String message)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"" + serverName + ":" + errorCode + "\": \"" + message + "\"}");
    }
}
