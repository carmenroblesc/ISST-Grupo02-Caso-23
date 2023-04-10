package es.upm.dit.isst.medconweb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import es.upm.dit.isst.medconweb.model.Cita;
import es.upm.dit.isst.medconweb.model.Paciente;



@Controller
public class PacienteController {
    public final String MEDCON_MANAGER_STRING= "http://localhost:8083/";

        public static final String VISTA_LOGIN = "login_paciente";

        public static final String VISTA_INICIO = "inicio";
        public static final String VISTA_TICKET = "ticket";

        private RestTemplate restTemplate = new RestTemplate();
        

    @GetMapping("/pacientes")
    public String login() {

            return "redirect:/" + VISTA_LOGIN;

    }
   
    @GetMapping("/pacientes/registrar")
    public String registrar(Paciente paciente) {
        // Comprobar si el paciente tiene cita
        ResponseEntity<Cita[]> response = restTemplate.getForEntity(
                MEDCON_MANAGER_STRING + "/citas/pacientes/" + paciente.getCipa(),
                Cita[].class);
    
        if (response.getStatusCode() != HttpStatus.OK) {
            // Si se produce un error al obtener las citas, redirigir a la vista de login
            return "redirect:" + VISTA_LOGIN;
        }
    
        List<Cita> citas = Arrays.asList(response.getBody());
        if (citas.isEmpty()) {
            // Si el paciente no tiene cita, redirigir a la vista de login
            return "redirect:" + VISTA_LOGIN;
        }
    
        // Si el paciente tiene cita, obtener la cita y actualizar el estado de la consulta
        Cita cita = citas.get(0);
        
        //restTemplate.postForLocation(MEDCON_MANAGER_STRING + "/consultas/" + consulta.getId() + "/incrementa");   esto esta mal porque no se como sacar el id de la consulta para actualizarla,imagino que hay que sacarlo dada la cita
    
        // Asignar un IdEspera al paciente
        ResponseEntity<Paciente> responsePaciente = restTemplate.postForEntity(
                MEDCON_MANAGER_STRING + "/paciente/" + paciente.getCipa() + "/asignarIdEspera", null, Paciente.class);
    
        if (responsePaciente.getStatusCode() != HttpStatus.OK) {
            // Si se produce un error al asignar el IdEspera, redirigir a la vista de login
            return "redirect:" + VISTA_LOGIN;
        }
    
        Paciente pacienteActualizado = responsePaciente.getBody();
    
        // Ir a la vista de ticket con el IdEspera del paciente
        String idEspera = pacienteActualizado.getIdEspera();
        return "redirect:" + VISTA_TICKET + "?idEspera=" + idEspera;
    }
    
}

