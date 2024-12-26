package com.dnth_underdog_241.online_fashion_shopping.security.config;

import com.dnth_underdog_241.online_fashion_shopping.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        private final AuthenticationEntryPoint authenticationEntryPoint;

        private final AccessDeniedHandler accessDeniedHandler;

        @Bean
        public SecurityFilterChain createSecurityFilterChain(HttpSecurity httpSecurity)
                        throws Exception {
                return httpSecurity
                                .httpBasic(Customizer.withDefaults())
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(AMRMRegistry -> AMRMRegistry
                                                .requestMatchers(
                                                        "api/v1/auth/**",
                                                        "public/**",
                                                        "/home",
                                                        "/index",
                                                        "/contact",
                                                        "/about",
                                                        "/login",
                                                        "/signup",
                                                        "/shop",
                                                        "/details",
                                                        "/checkout",
                                                        "/cart"
                                                ).permitAll()

                                                .requestMatchers("/css/**", "/js/**", "/img/**","/fonts/**","/favicon.ico", "/webjars/**")
                                                .permitAll()


                                                .requestMatchers(HttpMethod.GET, "api/v1/brands/**")
                                                .permitAll()

                                                .requestMatchers("api/v1/users/**")
                                                .hasAnyRole("ADMIN", "CUSTOMER", "EMPLOYEE")

                                                .requestMatchers("api/v1/admins/**")
                                                .hasRole("ADMIN")

                                                .requestMatchers("api/v1/employees")
                                                .hasAnyRole("EMPLOYEE", "ADMIN")

                                                .requestMatchers("api/v1/customers/**")
                                                .hasAnyRole("CUSTOMER", "ADMIN", "EMPLOYEE")

                                                .anyRequest()
                                                .authenticated())

                                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                                                .authenticationEntryPoint(authenticationEntryPoint)
                                                .accessDeniedHandler(accessDeniedHandler))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        @Bean
        public PasswordEncoder createPasswordEncoder() {
                return new BCryptPasswordEncoder(12);
        }

        @Bean
        public AuthenticationManager createAuthenticationManager(
                        AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }


}


