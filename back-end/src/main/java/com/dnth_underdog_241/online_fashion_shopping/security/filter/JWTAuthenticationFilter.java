package com.dnth_underdog_241.online_fashion_shopping.security.filter;


import com.dnth_underdog_241.online_fashion_shopping.exception.InvalidTokenException;
import com.dnth_underdog_241.online_fashion_shopping.security.service.WebUserDetailsService;
import com.dnth_underdog_241.online_fashion_shopping.security.util.JWTUtil;
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
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter
        extends OncePerRequestFilter
{
    private final WebUserDetailsService webUserDetailsService;


    private final JWTUtil jwtUtil;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.server.name}")
    private String serverName;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        try
        {
            Optional<String> token = Optional.empty();
            final String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
            {
                token = Optional.of(authorizationHeader.substring(7));
                if (jwtUtil.validateToken(token.get()))
                {
                    String subject = jwtUtil.extractAllClaims(token.get()).getSubject();

                    UserDetails userDetails = webUserDetailsService.loadUserByUsername(subject);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        catch(InvalidTokenException e)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"" + serverName +"-JWT_error_Known-Exception"+"\": \"" + e.getMessage() + "\"}");
            return;
        }
//        catch (Exception e)
//        {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"" + serverName +"-JWT_error_Unknown-Exception"+"\": \"Token is invalid. Login redirect.\"}");
//            return;
//        }
        filterChain.doFilter(request, response);
    }
}
