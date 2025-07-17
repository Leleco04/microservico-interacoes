package com.example.microservice_interactions.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                // Desativa a proteção csrf
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite uso de frames (H2 precisa)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll() // Libera todas as rotas do H2
                        .requestMatchers("/review/book/{bookId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/review/add").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/checklist/checklistUsuario/{userId}/{status}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/checklist/checklistUsuario/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/review/user/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/review/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/like/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,  "/like/existe/{userId}/{bookId}").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/like/{userId}/{bookId}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/checklist/checklistUsuario/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}