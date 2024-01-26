package com.airport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.airport.security.AuthServiceOauth;
import com.airport.security.GithubPrincipalExtractor;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService(PasswordEncoder
	 * passwordEncoder) { UserDetails user =
	 * User.withUsername("user").password(passwordEncoder.encode("password")).roles(
	 * "USER").build();
	 * 
	 * UserDetails admin =
	 * User.withUsername("admin").password(passwordEncoder.encode("admin")).roles(
	 * "USER", "ADMIN") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user, admin); }
	 */

	@Autowired
	AuthServiceOauth authServiceOauth;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.requestMatchers(new AntPathRequestMatcher("/user/**")).hasAnyRole("ADMIN", "USER", "AVIOCOMPANY")
				.requestMatchers(new AntPathRequestMatcher("/booking/**")).hasAnyRole("ADMIN", "USER", "AVIOCOMPANY")
				.requestMatchers(new AntPathRequestMatcher("/avio/**")).hasAnyRole("ADMIN", "AVIOCOMPANY")
				.requestMatchers(new AntPathRequestMatcher("/api/login")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/register")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/**")).hasAnyRole("ADMIN", "USER", "AVIOCOMPANY")
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login.jsp")
						.permitAll()
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/"))
				.logout(l -> l.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
				.csrf(csfr -> csfr.disable())
				.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login.jsp")
						.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(authServiceOauth)));
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	PrincipalExtractor gitHubPrincipalExtractor() {
		return new GithubPrincipalExtractor();
	}

}