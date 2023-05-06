package es.upm.dit.isst.medconweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private CustomAuthenticationProvider authProvider;
  
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .authorizeRequests()
           /** .antMatchers("/medicos/lista").hasRole("MEDICO") **/
			.antMatchers("/css/**", "/img/**", "/js/**").permitAll()
			.antMatchers("/sala", "/pacientes/**", "/medicos/**").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().permitAll()
        .and()
            .formLogin()
		    .permitAll()
		.and()
            .logout()
			.permitAll();
	}
}
