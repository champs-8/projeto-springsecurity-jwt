package com.champs.jwtdio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // ✅ ATIVA @PreAuthorize
public class WebSecurityConfig {

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // ✅ ATIVA CORS
            .csrf(AbstractHttpConfigurer::disable) // ✅ DESATIVA CSRF
            .headers(headers ->
                    headers.frameOptions(HeadersConfigurer
                            .FrameOptionsConfig::sameOrigin)) // ✅ PERMITE H2 CONSOLE
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ DESATIVA SESSÕES
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(SWAGGER_WHITELIST).permitAll() // ✅ PERMITE SWAGGER
                    .requestMatchers("/h2-console/**").permitAll() // ✅ PERMITE H2 CONSOLE
                    .requestMatchers(HttpMethod.POST, "/login").permitAll() // ✅ PERMITE LOGIN
                    .requestMatchers(HttpMethod.POST, "/users").permitAll() // ✅ PERMITE CRIAÇÃO DE USUÁRIOS
                    .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("USER", "ADMIN") // ✅ PERMITE USUÁRIOS E ADMINISTRADORES
                    .requestMatchers("/managers").hasRole("ADMIN") // ✅ PERMITE APENAS ADMIN
                    .anyRequest().authenticated() // ✅ REQUISIÇÕES AUTENTICADAS
            )
            .addFilterAfter(jwtFilter(), UsernamePasswordAuthenticationFilter.class); // ✅ ADICIONA FILTRO JWT

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**"
    };

}


