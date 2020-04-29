package com.yamada.springboot.app.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/rest/**").permitAll();
		
		//	CSRFを無効にするURLを設定
		RequestMatcher csrfMatcher = new RestMatcher("/rest/**");
		//	RESTのみCSRF対策を無効に設定		
		http.csrf().requireCsrfProtectionMatcher(csrfMatcher);
			
	}

}
