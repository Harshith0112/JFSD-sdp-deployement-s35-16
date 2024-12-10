package com.studentmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.studentmanagement.service.StudentService;
import com.studentmanagement.service.TeacherService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// student and teacher login credentials are stored in mysql db, while admin username and password defined below as in-memory
		
		auth
			.userDetailsService(studentService);
		auth
			.userDetailsService(teacherService);
		
		auth.inMemoryAuthentication()  // admin password username
			.withUser("admin")
			.password("{noop}1") // Use {noop} to indicate no password encoding
			.roles("ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN") // user with student or teacher role cannot access url starting with admin
			.antMatchers("/student/**").hasRole("STUDENT")
			.antMatchers("/teacher/**").hasRole("TEACHER")
			.and()
			.formLogin()
				.loginPage("/showLoginPage") // custom login page is generated in LoginController
				.loginProcessingUrl("/authenticateTheUser") // authenticateTheUser is automatically done by spring boot
				.successHandler(customAuthenticationSuccessHandler) // after login, user is redirected to home page depending on the role.
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied"); // simple access denied mapping defined in LoginController in case of user
	}
}
