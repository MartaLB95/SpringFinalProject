package com.tokio.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration

//Para que se aplique antes que la WebConfig
@Order(1)

public class RestSecurityConfig {
}
