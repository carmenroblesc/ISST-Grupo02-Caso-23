package es.upm.dit.isst.medconweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .antMatchers("/medicos/**", "/llamar/**", "/finalizar/**", "/historial/**", "/recetas/**", "/pruebas/**", "/citas/**").hasRole("MEDICO")
                .antMatchers("/pacientes/**").permitAll()
                .antMatchers("/sala").permitAll()
                .antMatchers("/css/**", "/img/**", "/js/**" ).permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated().and()
            .formLogin()
            .loginPage("/medicos")
               .permitAll()
               .and()
            .logout().permitAll().and()
            .httpBasic();
    } 

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
