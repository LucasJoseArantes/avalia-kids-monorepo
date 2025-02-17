package com.avaliakids.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.avaliakids.auth_service.security.JwtRequestFilter;

@Configuration
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // 🔹 Desabilita CSRF para chamadas API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔹 API stateless
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/auth/register", "/auth/validate-password").permitAll() // 🔹 Permite acesso público
                .requestMatchers(HttpMethod.GET, "/questions/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/questions/add").hasRole("TEACHER") // 🔹 Apenas TEACHER pode adicionar questões
                .requestMatchers("/swagger-ui/*", "/v3/api-docs/*").permitAll() // 🔹 Permite acesso ao Swagger
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin.disable()) // 🔹 Remove autenticação baseada em formulário
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // 🔹 Adiciona o filtro JWT
            .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}