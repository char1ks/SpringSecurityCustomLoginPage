package com.example.CustomLoginFormSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.CustomLoginFormSecurity.Service.*;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final adminService adminService;

    @Autowired
    public SecurityConfig(adminService adminService) {
        this.adminService = adminService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/admin/login", "/register").permitAll() // Используем requestMatchers вместо antMatchers
                .anyRequest().authenticated() // Все остальные страницы требуют аутентификации
                .and()
                .formLogin()
                .loginPage("/admin/login") // Указать кастомную страницу логина
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/product",true)
                .permitAll() // Разрешить доступ к странице логина
                .and()
                .logout()
                .permitAll(); // Разрешить выход

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(adminService);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}