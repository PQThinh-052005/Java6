package com.j6d6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {
	/*--Quản lý người dữ liệu người sử dụng--*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser("user1").password(pe.encode("123")).roles("GUEST")
			.and()
			.withUser("user2").password(pe.encode("123")).roles("USER", "GUEST")
			.and()
			.withUser("user3").password(pe.encode("123")).roles("ADMIN", "USER", "GUEST");
	}
	
	/*--Phân quyền sử dụng và hình thức đăng nhập--*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF, CORS
		http.csrf().disable().cors().disable();
		
		// Phân quyền sử dụng
		http.authorizeRequests()
			.antMatchers("/home/admins").hasRole("ADMIN")
			.antMatchers("/home/users").hasAnyRole("ADMIN", "USER")
			.antMatchers("/home/authenticated").authenticated()
			.anyRequest().permitAll(); // anonymous
		
		// Điều khiển lỗi truy cập không đúng vai trò
		http.exceptionHandling()
			.accessDeniedPage("/auth/access/denied"); // [/error]
		
		// Giao diện đăng nhập
		http.formLogin()
			.loginPage("/auth/login/form")
			.loginProcessingUrl("/auth/login") // [/login]
			.defaultSuccessUrl("/auth/login/success", false)
			.failureUrl("/auth/login/error")
			.usernameParameter("username") // [username]
			.passwordParameter("password"); // [password]
		http.rememberMe()
			.rememberMeParameter("remember"); // [remember-me]
		
		// Đăng xuất
		http.logout()
			.logoutUrl("/auth/logoff") // [/logout]
			.logoutSuccessUrl("/auth/logoff/success");	
	}
	
	/*--Mã hóa mật khẩu--*/
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*--Cho phép request đến REST API từ browser--*/
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}