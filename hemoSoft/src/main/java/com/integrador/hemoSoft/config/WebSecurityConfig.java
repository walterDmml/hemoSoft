package com.integrador.hemoSoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.integrador.hemoSoft.security.GpUserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private GpUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/enfermeiro/cadastro").hasAuthority("ADMIN")
			.antMatchers("/doador/cadastro").hasAuthority("ADMIN")
			/*.antMatchers("/enfermeiro/lista").hasAnyRole("ADMIN")
			.antMatchers("/doador/cadastro").hasAnyRole("ADMIN")
			.antMatchers("/doador/lista").hasAnyRole("ADMIN").
			antMatchers("/bolsa/lista").hasAnyRole("ADMIN")*/
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/entrar")
			.permitAll()
		.and()
		.logout()
			.logoutSuccessUrl("/entrar?logout")
			.permitAll()
		.and()
		.rememberMe()
			.userDetailsService(userDetailsService);
	}

}
