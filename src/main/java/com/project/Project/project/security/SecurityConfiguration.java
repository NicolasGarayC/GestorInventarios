package com.project.Project.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/usuarios/**").permitAll() // Permitir todas las rutas bajo /api/usuarios
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults()); // Configuración básica de autenticación HTTP

        return http.build();
    }

}
