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
            .requestMatchers("/home/index", "/home/about", "/login", "/logout").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login") // Tự custom trang login
            .defaultSuccessUrl("/home/index", true) // Đăng nhập thành công về trang chính
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout") // URL logout
            .logoutSuccessUrl("/home/index") // Sau khi logout thì về lại index
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        );
    return http.build();
}


    // Nếu cần dùng AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
