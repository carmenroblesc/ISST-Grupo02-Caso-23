package es.upm.dit.isst.medconweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.medconweb.model.Medico;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public final String MEDCONMANAGER_STRING = "http://localhost:8083/";
    private RestTemplate restTemplate = new RestTemplate();
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medico medico = medicoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEDICO"));
        
        return User.builder()
        .username(medico.getUsername())
        .password(medico.getPassword())
        .roles(medico.getRoles())
        .build();

    }
}
