package com.amouamado.amou_amado.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desabilita proteção CSRF para testes
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()   // Permite acesso livre a todas as rotas
            );

        return http.build();
    }
}
