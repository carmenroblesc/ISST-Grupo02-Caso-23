package es.upm.dit.isst.medconweb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import es.upm.dit.isst.medconweb.model.Cita;
import es.upm.dit.isst.medconweb.model.Consulta;
import es.upm.dit.isst.medconweb.model.Medico;
import es.upm.dit.isst.medconweb.model.Paciente;

@Controller
public class GeneralController {
    public final String MEDCONMANAGER_STRING = "http://localhost:8083/";
    public static final String VISTA_INICIAL = "inicio";
    public static final String VISTA_LOGIN_PACIENTE = "loginPaciente";
    public static final String VISTA_LOGIN_MEDICO = "loginMedico";
    public static final String VISTA_TICKET = "ticket";
    public static final String VISTA_SALA_ESPERA = "salaEspera";
    public static final String VISTA_LISTA = "lista";

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String inicio() {
        return VISTA_INICIAL;
    }

    // PACIENTES

    @GetMapping("/pacientes")
    public String login_pad(Map<String, Object> model) {
       Paciente paciente= new Paciente();
       model.put("paciente", paciente);
       return VISTA_LOGIN_PACIENTE;
    }

    @PostMapping("/pacientes/registrar")
    public String registrar(Paciente paciente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VISTA_LOGIN_PACIENTE;
        }
        String cipa = paciente.getCipa();
        // Comprobar si el paciente tiene cita
        try {
            Cita cita = restTemplate.getForObject(MEDCONMANAGER_STRING + "citas/pacientes/" + cipa, Cita.class);
            if (cita == null) {
                return VISTA_LOGIN_PACIENTE;
            } else {
                Paciente p = cita.getPaciente();
                // Asignar un IdEspera al paciente
                restTemplate.postForObject(MEDCONMANAGER_STRING + "/pacientes/" + cipa + "/asignarIdEspera", p, Paciente.class);
                // Actualizar estado del paciente
                restTemplate.postForObject(MEDCONMANAGER_STRING + "/pacientes/" + cipa + "/registrar", p, Paciente.class);
                Cita citaActualizada = restTemplate.getForObject(MEDCONMANAGER_STRING + "citas/pacientes/" + cipa, Cita.class);
                Paciente pacienteActualizado = citaActualizada.getPaciente();
                // Pasar paciente a la vista
                model.addAttribute("paciente", pacienteActualizado);
                Consulta consulta = restTemplate.getForObject(MEDCONMANAGER_STRING + "consultas/pacientes/" + cipa, Consulta.class);
                model.addAttribute("salaEspera", consulta.getSalaEspera());
            }
        } catch (Exception e) {
        }
        return VISTA_TICKET;
    }

    // SALA DE ESPERA

    @GetMapping("/sala/{id}")
    public String sala(@PathVariable(value = "id") Integer idSala, Model model) {
        List<Consulta> consultasEnEspera = new ArrayList<Consulta>();
        List<Consulta> consultasEnTurno = Arrays
                .asList(restTemplate.getForEntity(MEDCONMANAGER_STRING + "/consultas/sala/" + idSala + "/" + 1, Consulta[].class).getBody());
        List<Consulta> consultasEnEsperaAux = Arrays
                .asList(restTemplate.getForEntity(MEDCONMANAGER_STRING + "/consultas/sala/" + idSala + "/" + 0, Consulta[].class).getBody());
        for (Consulta consulta : consultasEnEsperaAux) {
            if (consulta.getPaciente().getPresente())
                consultasEnEspera.add(consulta);
        }
        model.addAttribute("consultasEnTurno", consultasEnTurno);
        model.addAttribute("consultasEnEspera", consultasEnEspera);
        model.addAttribute("salaEspera", idSala);
        return VISTA_SALA_ESPERA;
    }


    // MÉDICOS
    /** @GetMapping("/medicos")
    public String login_med() {
       return "login";
    }
  
    @GetMapping("/medicos/lista")
    public String lista(Model model, Principal principal) {
        String idMedico = principal.getName();
        List<Consulta> consultas = Arrays.asList(restTemplate
                .getForEntity(MEDCONMANAGER_STRING + "/consultas/medicos/" + idMedico, Consulta[].class).getBody());
        // le pasamos a la vista todas las consultas del médico para que tenga la agenda completa
        // debe ser la vista la que se encargue de marcar el estado de cada una
        model.addAttribute("consultas", consultas);
        return VISTA_LISTA;
    }

    **/
    @GetMapping("/medicos")
    public String login_med(Map<String, Object> model) {
       Medico medico = new Medico();
       model.put("medico", medico);
       return VISTA_LOGIN_MEDICO;
    }

    @PostMapping("/medicos/lista")
    public String lista(Medico medicoaux, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VISTA_LOGIN_MEDICO;
        }
        String idMedico = medicoaux.getNcolegiado();
        Medico medico = restTemplate.getForObject(MEDCONMANAGER_STRING + "/medicos/" + idMedico, Medico.class);
    
        if (!medicoaux.getPassword().equals(medico.getPassword())) {
            return VISTA_LOGIN_MEDICO;
        }
        List<Consulta> consultas = Arrays.asList(restTemplate
                .getForEntity(MEDCONMANAGER_STRING + "/consultas/medicos/" + idMedico, Consulta[].class).getBody());
        // le pasamos a la vista todas las consultas del médico para que tenga la agenda completa
        // debe ser la vista la que se encargue de marcar el estado de cada una
        model.addAttribute("consultas", consultas);
        return VISTA_LISTA;
    }

    @GetMapping("/llamar/{id}")
    public String llamar(@PathVariable(value = "id") Long idConsulta, Model model) {
        try {
            Consulta consulta = restTemplate.getForObject(MEDCONMANAGER_STRING + "/consultas/" + idConsulta, Consulta.class);
            if (consulta != null) {
                restTemplate.postForObject(MEDCONMANAGER_STRING + "/consultas/" + consulta.getId() + "/incrementa", consulta, Consulta.class);
                String idMedico = consulta.getMedico().getNcolegiado();
                List<Consulta> consultas = Arrays.asList(restTemplate.getForEntity(MEDCONMANAGER_STRING + "/consultas/medicos/" + idMedico, Consulta[].class).getBody());
                model.addAttribute("consultas", consultas); 
            }
        } catch (HttpClientErrorException.NotFound ex) {}
        return VISTA_LISTA;
    }

    @GetMapping("/finalizar/{id}") 
    public String finalizar(@PathVariable(value = "id") Long idConsulta, Model model) {
        try {
            Consulta consulta = restTemplate.getForObject(MEDCONMANAGER_STRING + "/consultas/" + idConsulta, Consulta.class);
            if (consulta != null) {
                restTemplate.postForObject(MEDCONMANAGER_STRING + "/consultas/" + consulta.getId() + "/finaliza", consulta, Consulta.class);
                String idMedico = consulta.getMedico().getNcolegiado();
                List<Consulta> consultas = Arrays.asList(restTemplate.getForEntity(MEDCONMANAGER_STRING + "/consultas/medicos/" + idMedico, Consulta[].class).getBody());
                model.addAttribute("consultas", consultas); 
            }
        } catch (HttpClientErrorException.NotFound ex) {}
        return VISTA_LISTA;
    } 
    
    @GetMapping("/historial")
    public String historial() {
        return "GestionarHistoriales";
    }

    @GetMapping("/recetas")
    public String recetas() {
        return "GestionarRecetas";
    }

    @GetMapping("/pruebas")
    public String pruebas() {
        return "GestionarPruebas";
    }

    @GetMapping("/citas")
    public String citas() {
        return "GestionCitas";
    } 
}
