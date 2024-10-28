package com.dnth_underdog_241.online_fashion_shopping.security.config;


import com.dnth_underdog_241.online_fashion_shopping.security.service.WebUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration
{
    private final WebUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain createSecurityFilterChain(HttpSecurity httpSecurity)
            throws Exception
    {
        return httpSecurity
                .csrf(csrfConfigurer ->
                        csrfConfigurer.disable())
                .authorizeHttpRequests(AMRMRegistry ->
                        AMRMRegistry
                                .requestMatchers("api/v1/auth/*")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }


    @Bean
    public PasswordEncoder createPasswordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }
}
