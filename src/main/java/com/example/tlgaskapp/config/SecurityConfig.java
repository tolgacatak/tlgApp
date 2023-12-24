package com.example.tlgaskapp.config;

import com.example.tlgaskapp.security.JwtAuthenticationEntryPoint;
import com.example.tlgaskapp.security.JwtAuthenticationFilter;
import com.example.tlgaskapp.services.UserDetailsServiceImplementation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsServiceImplementation userDetailsServiceImplementation;
    private JwtAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation, JwtAuthenticationEntryPoint handler) {
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
        this.handler = handler;
    }
    @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter(){
            return new JwtAuthenticationFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CorsFilter corsFilter(){ //izinler verildi
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.exceptionHandling(e-> e.authenticationEntryPoint(handler))
                .addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf->csrf.disable()) //test amaçlı disable, normalde önerilmez!
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/auth/*")//auth ile alakalı herhangi bir request olduğunda
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/posts/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/posts")
                        .permitAll()
                        .anyRequest().authenticated() //bunun dışındakiler auth. olmalı
                ).sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
