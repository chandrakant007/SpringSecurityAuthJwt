package com.ck.practice.SpringSecurityAuthJwt.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ck.practice.SpringSecurityAuthJwt.Filter.JwtAuthenticationFilter;
import com.ck.practice.SpringSecurityAuthJwt.proxy.Dummy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	// Spring will find your UserDetailsDaoImpl automatically because it implements
	// UserDetailsService
	public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	// Replace from above
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//    	return http.csrf(customize -> customize.disable())
//				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
//				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

//    	return http.csrf(customize -> customize.disable())
//				.authorizeHttpRequests(auth -> auth
//					    .anyRequest().permitAll()
//						).build();

		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/login").permitAll()
						.requestMatchers("/auth/login/test").permitAll() // remove later
						.requestMatchers("/error").permitAll().requestMatchers("/public/**").permitAll().anyRequest()
						.authenticated())
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	
}

/**
 **
 *
 * @Configuration : This Configuration annotation tells Spring that whatever
 *                method with @bean meantioned here, treat as bean
 * @EnableWebSecurity : after spring boot 3/framework 6 - not much usefull, it
 *                    is just usefull for bifurgation only Initial versio it is
 *                    must required for enable websecrity, but letest version it
 *                    is come with SecurityAutoConfiguration
 *
 * @AuthenticationProvider (Low Level) : This is interface, will provide user
 *                         details. Use for spring injection
 *                         DaoAuthenticationProvider : is just implementation of
 *                         AuthenticationProvider(internal) helps to load
 *                         username/PW and provide UserDetailsService :
 *                         Interface having one method loadUserByUsername which
 *                         will implement in custome dao
 * 
 * @AuthenticationManager (High Level): Manage all provider like
 *                        AuthenticationProvider(DaoAuthenticationProvider),
 *                        JwtAuthenticationProvider,
 *                        OAuth2AuthenticationProvider, etc.
 *                        AuthentcationManager AND AuthenticationProvider both
 *                        Interface having one method authentic
 *
 * @BCryptPasswordEncoder : encode or hash the password, eachtime you get
 *                        differernt PW.
 *
 * @Question : if 2 DaoImpl for same UserServiceDetail interface then build
 *           failed - no unique bean error Solution: 1. @Primary, 2. @qualifier,
 *           3. defineDaoImpl instead interface in configuration class
 * 
 * 
 * @injection : 
 * 
 * @constructor : If not a bean, failed in Build time. Helpful when works with Mockito ot test framework
 * @annotation autowire: also failed while build. helpful when many dependency
 * @setter: not fail, helpful when not sure about dependency, helpful in optional dependecy
 * 
 */