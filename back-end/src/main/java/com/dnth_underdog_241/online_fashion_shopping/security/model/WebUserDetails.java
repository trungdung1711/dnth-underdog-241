package com.dnth_underdog_241.online_fashion_shopping.security.model;


import com.dnth_underdog_241.online_fashion_shopping.model.user.WebUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@AllArgsConstructor
@Builder
@Getter
@Setter
public class WebUserDetails implements UserDetails
{
    private WebUser webUser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return webUser.getRoles();
    }


    @Override
    public String getPassword()
    {
        return webUser.getPassword();
    }


    @Override
    public String getUsername()
    {
        return webUser.getPhoneNumber();
    }


    public Long getId()
    {
        return webUser.getId();
    }
}
