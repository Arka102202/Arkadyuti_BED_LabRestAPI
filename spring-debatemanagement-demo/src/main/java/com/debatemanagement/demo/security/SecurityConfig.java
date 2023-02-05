package com.debatemanagement.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain createSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/","index","/css/*", "/js/*").permitAll()
                .antMatchers(
                        "/student-dash-board/students",
                        "/student-dash-board/edit-page/**",
                        "/student-dash-board/view-page/**",
                        "/student-dash-board/new-update-student").hasAnyAuthority("ADMIN","USER")
                .mvcMatchers( "/student-dash-board/delete-student/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/student-dash-board/students").permitAll()
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedPage("/student-dash-board/error")
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();


        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}