package com.geeksforless.client.configuation.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/registration")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher( "/webjars/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher( "/p")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true))
                .logout(logout -> logout.permitAll());

        return http.build();
    }
  }
