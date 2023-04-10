package es.upm.dit.isst.medconweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


@Controller
public class MedicoController {
    public final String MEDCON_MANAGER_STRING= "http://localhost:8083/";

    public static final String VISTA_LOGIN_MEDICOS = "login";
    public static final String VISTA_LISTA = "lista";
    public static final String VISTA_LISTA_MEDICOS = "lista";

    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/medicos")
    public String login_med() {

            return "redirect:/" + VISTA_LOGIN_MEDICOS;

    }

    @GetMapping("/medicos/entrar")
    public String entrar_med() {

            return "redirect:/" + VISTA_LISTA;

    }

    @GetMapping("/medicos/lista")//SIN HACER,no entiendo lo del papel de Cova
    public String lista_med() {

            return "redirect:/" + VISTA_LISTA_MEDICOS;

    }
    @GetMapping("/medicos/llamar/{id}")//Teóricamente bien
    public String  llamar(@PathVariable(value = "id") Long id) {
    String consultaEndpoint = MEDCON_MANAGER_STRING + "/consultas/" + id + "/incrementa";
    restTemplate.postForLocation(consultaEndpoint, null);
    return "redirect:/" + VISTA_LISTA_MEDICOS;
}


@GetMapping("/medicos/finalizar/{id}")//Teóricamente bien
public String finalizar(@PathVariable(value = "id") Long idConsulta) {
    String url = MEDCON_MANAGER_STRING + "/consultas/" + idConsulta + "/finaliza";
    restTemplate.postForObject(url, null, ResponseEntity.class);
    return "redirect:/" + VISTA_LISTA_MEDICOS;
}

    
}

