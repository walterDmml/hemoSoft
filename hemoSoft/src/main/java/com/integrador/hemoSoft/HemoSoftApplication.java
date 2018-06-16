package com.integrador.hemoSoft;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@ComponentScan(basePackages = {"com.integrador.hemoSoft.controller", "com.integrador.hemoSoft.service",
		"com.integrador.hemoSoft.repository","com.integrador.hemoSoft.config"})
public class HemoSoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemoSoftApplication.class, args);
	}
	
	@Bean
	public org.springframework.web.servlet.LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
}
