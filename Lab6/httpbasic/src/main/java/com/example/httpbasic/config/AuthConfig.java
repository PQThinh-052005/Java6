package com.example.httpbasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(BCryptPasswordEncoder encoder) {
        UserDetails user1 = User.withUsername("user1")
                .password(encoder.encode("123"))
                .roles("GUEST")
                .build();

        UserDetails user2 = User.withUsername("user2")
                .password(encoder.encode("123"))
                .roles("USER", "GUEST")
                .build();

        UserDetails user3 = User.withUsername("user3")
                .password(encoder.encode("123"))
                .roles("ADMIN", "USER", "GUEST")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/loginform", "/home/index").permitAll() 
                        .requestMatchers("/home/admin").hasRole("ADMIN") // Chỉ admin mới vào được
                        .requestMatchers("/home/user").hasAnyRole("USER", "ADMIN") // Chỉ user và admin mới vào được
                        .requestMatchers("/home/guest").hasAnyRole("GUEST", "USER", "ADMIN") // Chỉ guest, user và admin mới vào được
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/home/index"))
                .formLogin(form -> form
                        .loginPage("/loginform")
                        .defaultSuccessUrl("/home/index", true) // Đăng nhập thành công về trang chính
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL logout
                        .logoutSuccessUrl("/home/index")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                  
                        ;
        return http.build();
    }

    // Nếu cần dùng AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
