package com.dms.base.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dms.base.filters.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Value("${cors.allowed-origins}") // Read allowed origins from properties
    private String[] allowedOrigins;

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITELIST = {
        // Auth endpoints
        "/v1/web/auth/login",
        "/v1/mobile/auth/login",
        
        // Public endpoints
        "/v1/public/**",
        
        // Web area endpoints
        "/v1/web/area/list",
        "/v1/web/area/new",
        // Web company endpoints
        "/v1/web/company/current",
        "/v1/web/company/new",
        
        // Web price endpoints
        "/v1/web/price/list",
        "/v1/web/price/new",
        "/v1/web/price/update/**",

        // Web users endpoints
        "/v1/web/users/current",
        "/v1/web/users/new",

        // Mobile packages endpoints
        "/v1/mobile/packages/new",
        "/v1/mobile/packages/update/**", // Use ** to match any path after update/
        
        // Mobile user endpoints
        "/v1/mobile/user/current",
        "/v1/mobile/user/profile",
        
        // Swagger documentation
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/swagger-resources/**"
    };

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // List your allowed origins here
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // important if you use cookies or Authorization headers
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
