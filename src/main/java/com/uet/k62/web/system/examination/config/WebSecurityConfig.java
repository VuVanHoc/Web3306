package com.uet.k62.web.system.examination.config;

import com.uet.k62.web.system.examination.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailServiceImpl userDetailService;
	private final PasswordEncoder passwordEncoder;
	
	public WebSecurityConfig(PasswordEncoder passwordEncoder, UserDetailServiceImpl userDetailService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailService = userDetailService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/api/users/**").permitAll()
//				.antMatchers("/home/**").permitAll()
				.antMatchers("/api/question-types/**").permitAll()
				.antMatchers("/api/questions/**").permitAll()
				.antMatchers("/api/answers/**").permitAll()
				.antMatchers("/api/course-types/**").permitAll()
				.antMatchers("/api/courses/**").permitAll()
				.antMatchers("/api/exams/**").permitAll()

                .antMatchers("/v2/api-docs",
						"/swagger-resources",
						"/swagger-resources/**",
						"/configuration/ui",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**", "/static/**").permitAll()
				.anyRequest().permitAll()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	//	cung cấp userDetailService và passwordEncoder cho Spring Security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
}
