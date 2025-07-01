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
                .csrf(csrf -> csrf.disable()) // Disable CSRF for development simplicity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // ✅ Public UI Pages
                        .requestMatchers("/", "/login", "/register").permitAll()

                        // ✅ Public Book & Collection Pages
                        .requestMatchers("/books/**").permitAll()
                        .requestMatchers("/collections/**").permitAll()
                        .requestMatchers("/collection-books/**").permitAll()

                        // ✅ Form Submissions (POST)
                        .requestMatchers(HttpMethod.POST, "/books/add").permitAll()
                        .requestMatchers(HttpMethod.POST, "/collections/add").permitAll()
                        .requestMatchers(HttpMethod.POST, "/collections/edit").permitAll()
                        .requestMatchers(HttpMethod.POST, "/collections/delete/**").permitAll()

                        // ✅ Static resources
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // ✅ Public API (if still using any endpoints under /api/**)
                        .requestMatchers("/api/**").permitAll()

                        // 🔐 Everything else requires authentication
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
