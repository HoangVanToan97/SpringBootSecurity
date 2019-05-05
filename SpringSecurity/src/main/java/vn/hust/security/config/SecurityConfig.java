package vn.hust.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from users where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register", "/", "/login").permitAll().antMatchers("/user/**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')").and().formLogin().loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login").failureUrl("/login?message=error").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/user").and().logout()
				.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout").and()
				.exceptionHandling().accessDeniedPage("/403").and();
		http.csrf().disable().authorizeRequests().and();
	}

}
