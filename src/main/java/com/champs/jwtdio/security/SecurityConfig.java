package com.champs.jwtdio.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {
    private String prefix;
    private String key;
    private long expiration;
}
