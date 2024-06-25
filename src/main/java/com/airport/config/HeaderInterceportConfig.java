package com.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.airport.interceptor.TokenInterceptor;

@Configuration
public class HeaderInterceportConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor());
	}
	
	@Bean
	TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}
}
