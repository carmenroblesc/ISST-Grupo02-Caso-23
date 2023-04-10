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
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.medconweb.model.Cita;
import es.upm.dit.isst.medconweb.model.Medico;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public final String MEDCONMANAGER_STRING = "http://localhost:8083/";
    private RestTemplate restTemplate = new RestTemplate();
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        String id = authentication.getPrincipal().toString(); 
        Cita cita = restTemplate.getForObject(MEDCONMANAGER_STRING + "citas/pacientes/" + id, Cita.class);
        Medico medico = restTemplate.getForObject(MEDCONMANAGER_STRING + "medicos/" + id, Medico.class);
        if (cita != null) {
            List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
            ga.add(new SimpleGrantedAuthority("ROLE_PACIENTE"));
            return new UsernamePasswordAuthenticationToken(id, "", ga);
        }
        if (medico != null) {
            List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
            ga.add(new SimpleGrantedAuthority("ROLE_MEDICO"));
            return new UsernamePasswordAuthenticationToken(id, "", ga);
        }
        throw new UsernameNotFoundException ("could not login");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}