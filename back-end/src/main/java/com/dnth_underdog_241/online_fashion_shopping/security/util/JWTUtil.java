package com.dnth_underdog_241.online_fashion_shopping.security.util;


import com.dnth_underdog_241.online_fashion_shopping.exception.InvalidTokenException;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;


@Component
public class JWTUtil
{
    @Value("${com.dnth_underdog_241.online_fashion_shopping.jwt.secret}")
    private String secretKey;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.jwt.expiration}")
    private Long expiration;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.jwt.issuer}")
    private String issuer;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.jwt.audience}")
    private String audience;


    private SecretKey key;


    @PostConstruct
    public void init()
    {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    public String generateToken(Long id, String phoneNumber, Set<Role> roles)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("roles", roles);
        return createToken(claims, phoneNumber);
    }


    public String createToken(Map<String, Object> claims, String phoneNumber)
    {
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(now.getTime() + expiration * 1000);

        return Jwts
                .builder()
                .claims(claims)
                .issuer(issuer)
                .subject(phoneNumber)
                .audience()
                .add(audience)
                .and()
                .expiration(exp)
                .notBefore(now)
                .issuedAt(now)
                .signWith(key)
                .compact();
    }


    public Claims extractAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public boolean isIssuerValid(String token)
    {
        return issuer
                .equals(extractAllClaims(token).getIssuer());
    }


    public boolean isSubjectValid(String token)
    {
        return true;
    }


    public boolean isAudienceValid(String token)
    {
        return true;
    }


    public boolean isNotExpired(String token)
    {
        return extractAllClaims(token)
                .getExpiration()
                .after(new Date());
    }


    public boolean isNotBeforeValid(String token)
    {
        return true;
    }


    public boolean validateToken(String token)
    {
        if (!isIssuerValid(token))
        {
            throw new InvalidTokenException("Invalid issuer.");
        }
        if (!isSubjectValid(token))
        {
            throw new InvalidTokenException("Invalid subject.");
        }
        if (!isAudienceValid(token))
        {
            throw new InvalidTokenException("Invalid audience.");
        }
        if (!isNotExpired(token))
        {
            throw new InvalidTokenException("Token has expired.");
        }
        if (!isNotBeforeValid(token))
        {
            throw new InvalidTokenException("Token is not valid yet.");
        }
        return true;
    }
}
