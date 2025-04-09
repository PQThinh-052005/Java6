package poly.httpbasic.lab6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public void configureUsers(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = encoder();
        auth.inMemoryAuthentication()
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/api/**").hasRole("ADMIN")
            .antMatchers("/products").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();

        return http.build();
    }
        http.authorizeRequests()
            .antMatchers("/api/**").hasRole("ADMIN")
            .antMatchers("/products").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }
}
