package com.siwyus.qrcontainer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)  // disable CSRF protection for test purposes
                .authorizeHttpRequests((requests) -> requests
                        // todo remove endpoints other than auth
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/api/containers", "/api/containers/**", "/api/items", "/api/items/**").permitAll()  // allow access to registration and login endpoints
                        .anyRequest().authenticated()  // require authentication for all other requests
                ).httpBasic(Customizer.withDefaults());  // use basic authentication

        return http.build();
    }
}