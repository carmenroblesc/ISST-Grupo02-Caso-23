package es.upm.dit.isst.medconweb.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public final String MEDCONMANAGER_STRING = "http://localhost:8083/";
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        String name = authentication.getPrincipal().toString(); 
        if (name.length() == 4) {
            List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
            ga.add(new SimpleGrantedAuthority("ROLE_MEDICO"));
            return new UsernamePasswordAuthenticationToken(name, "", ga);
        }
        if (name.length() == 6) {
            List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
            ga.add(new SimpleGrantedAuthority("ROLE_PACIENTE"));
            return new UsernamePasswordAuthenticationToken(name, "", ga);
        }
        throw new UsernameNotFoundException ("could not login");   
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    
}
