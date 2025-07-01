package com.amon.book_catalog.config;

import com.amon.book_catalog.security.JwtAuthenticationFilter;
import com.amon.book_catalog.service.implementation.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // ✅ Public UI Routes
                        .requestMatchers("/", "/login", "/register").permitAll()
                        .requestMatchers("/books/**", "/collections/**", "/collection-books/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/books/add").permitAll() // ✅ Fix for POST form

                        // ✅ Static Resources
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // ✅ Public API Routes
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/books/**").permitAll()
                        .requestMatchers("/api/collections/**").permitAll()
                        .requestMatchers("/api/collection-books/**").permitAll()

                        // 🔐 Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
