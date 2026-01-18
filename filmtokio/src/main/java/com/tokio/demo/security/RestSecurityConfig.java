package com.tokio.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration for the REST API.
 * This configuration uses JWT-based authentication and is fully stateless
 * (the user is not remembered and each request is independent),
 * allowing the API to be consumed by external clients.
 */
@Configuration

//Para que se aplique antes que la WebConfig
@Order(1)

public class RestSecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain restFilterChain(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/api/**") /**So that this configuration applies just to the API and not the MVC*/
                .csrf(csrf -> csrf.disable())/**No need because there are no session cookies*/
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/ratings/**").authenticated()
                        .anyRequest().denyAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
