package es.upm.dit.isst.medconweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .authorizeRequests()
			.antMatchers("/css/**", "/img/**", "/layouts/**").permitAll()
			.antMatchers("/", "/pacientes/**, /sala/**, /medicos/**").permitAll()
			.anyRequest().authenticated()
        .and()
            .formLogin()
				.loginPage("/pacientes")
				.loginPage("/medicos")
				.permitAll()
		.and()
            .logout()
			.permitAll();
	}
}