package com.dnth_underdog_241.online_fashion_shopping.util;


import com.dnth_underdog_241.online_fashion_shopping.security.model.WebUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebUserUtil
{
    public Long getAuthenticatedUserId()
    {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails)
        {
            return ((WebUserDetails) principal).getId();
        }
        else
        {
            throw new IllegalStateException("User is not authenticated");
        }
    }
}
