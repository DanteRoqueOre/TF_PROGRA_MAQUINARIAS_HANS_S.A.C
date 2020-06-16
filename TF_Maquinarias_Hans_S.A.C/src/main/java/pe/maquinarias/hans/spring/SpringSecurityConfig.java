package pe.maquinarias.hans.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.maquinarias.hans.spring.auth.handler.LoginSuccessHandler;
import pe.maquinarias.hans.spring.serviceimpl.JpaUserDetailsService;;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		try {
			http.authorizeRequests()

					.antMatchers("/modelo/**").access("hasRole('ROLE_ADMIN') ")
				
					.antMatchers("/tipo/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/cargo/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/especialidad/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/maquinista/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/administrador/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/maquinaria/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/marca/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
					.antMatchers("/alquiler/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and()
					.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/marca/listar")
					.permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/error_403");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
