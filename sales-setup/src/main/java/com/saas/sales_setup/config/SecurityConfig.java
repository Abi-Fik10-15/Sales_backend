package com.saas.sales_setup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class configures Spring Security for our application.
 * @EnableWebSecurity enables Spring's web security support.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Allow unauthenticated access to Swagger UI and API docs for testing.
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Require a valid, authenticated token for all other requests.
                        .anyRequest().authenticated()
                )
                // Configure the app as an OAuth2 Resource Server, enabling JWT validation.
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}