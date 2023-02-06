package com.example.authenticatingldap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/registration**",
						"/update-user-info/**",
						"/update-user/**",
						"/view-users/**",
						"/delete/**",
				        "/login/**",
				        "/admin/**",
						"/welcome/**",
						"/user**",
						"/restrict/**",
						"/block/**",
						"/js/**",
						"/css/**",
						"/img/**").permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin()
				.loginPage("/welcome")
				.defaultSuccessUrl("/index.html", true)
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/welcome?logout");
	}

	/*@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")
				.groupSearchBase("ou=groups")
				.contextSource()
					.url("ldap://localhost:8389/dc=springframework,dc=org")
					.and()
				.passwordCompare()
					.passwordEncoder(new BCryptPasswordEncoder())
					.passwordAttribute("userPassword");
	}*/

}
