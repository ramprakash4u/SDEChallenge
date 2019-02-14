package com.paytmlabs.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
			
			"/swagger-resources/**",
			"/swagger-ui-html",
			"/v1/api-docs",
			"/webjars/**",
			"**/get/stream/moving-average/**"
	};
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.x509();
		http.httpBasic().disable().csrf().disable();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/ApplicationHealth");
		web.ignoring().antMatchers("/get/stream/moving-average/last/**");
		web.ignoring().antMatchers(AUTH_WHITELIST);
		
	}
	
}
