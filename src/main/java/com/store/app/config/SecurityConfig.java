package com.store.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration of Spring Security
 * 
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	/**
	 * Creates admin with admin role
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin);
	}

	/**
	 * Filter that states which endpoint should be visible to all
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/").permitAll();
			auth.requestMatchers("/h2-console/**").permitAll();
			auth.requestMatchers("/api/guest/**").permitAll();
			auth.requestMatchers("/images/**").permitAll();
			auth.requestMatchers("/images/games/**").permitAll();
			auth.requestMatchers("/view/**").permitAll();
			auth.requestMatchers("/view/page/**").permitAll();
			auth.requestMatchers("/view/page/game/**").permitAll();
			auth.requestMatchers("/api/admin/**").hasRole("ADMIN");
		}).httpBasic(Customizer.withDefaults()).build();

	}
}
