package com.swastha.swastha_track.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/*
This class is responsible for configuring the security settings of the application. It defines how HTTP requests are secured and how authentication is handled.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    //Bean created for passwordEncoder and BCrypt used as it Hashes passwords securely
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) //CSRF protection is turned off 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() //Requests to /auth/** endpoints are allowed without authentication, all other needs authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //Adds your custom jwtFilter before the standard username/password authentication filter.

        return http.build(); //builds and returns the security filter chain
    }
}
