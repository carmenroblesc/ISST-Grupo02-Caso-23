package es.upm.dit.isst.medconweb.controller;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.medconweb.model.*;

public class CitaController {
    public final String CITAMANAGER_STRING = "http://localhost:8083/citas/";
    //"main menu" del ordenador del medico
    public static final String VISTA_LISTA = "lista";
    //formulario para concertar cita
    public static final String VISTA_FORMULARIO = "GestionCitas";
    private RestTemplate restTemplate = new RestTemplate();

    //1. CREAR CITA
    //1.1 Devolver Formulario
    @GetMapping("/crear")
    public String crear(Map<String, Object> model) {
        Cita Cita = new Cita();
        model.put("Cita", Cita);
        model.put("accion", "guardar");
        return VISTA_FORMULARIO;
    }

    //1.2 Validar Formulario y guardar cita
    @PostMapping("/guardar")
    public String guardar(@Validated Cita Cita, BindingResult result) {
        if (result.hasErrors()) {
            return VISTA_FORMULARIO;
        }
        try {
            restTemplate.postForObject(CITAMANAGER_STRING, Cita, Cita.class);
        } catch (Exception e) {
        }
        return "redirect:" + VISTA_LISTA;
    }

}
