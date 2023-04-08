package es.upm.dit.isst.medconapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.medconapi.model.Cita;
import es.upm.dit.isst.medconapi.repository.CitaRepository;

@RestController
public class CitaController {
    private final CitaRepository citaRepository;
    public static final Logger log = LoggerFactory.getLogger(CitaController.class);

    public CitaController(CitaRepository t) {
            this.citaRepository = t;
    }

    // CREAR

    // crea una cita
    @PostMapping("/citas")
    ResponseEntity<Cita> create(@RequestBody Cita newCita) throws URISyntaxException {
        Cita result = citaRepository.save(newCita);
        return ResponseEntity.created(new URI("/citas/" + result.getId())).body(result);
    }

    // LEER

    // devuelve una cita concreta
    @GetMapping("/citas/{id}")
    ResponseEntity<Cita> read(@PathVariable Long id) {
        return citaRepository.findById(id).map(cita -> ResponseEntity.ok().body(cita))
                .orElse(new ResponseEntity<Cita>(HttpStatus.NOT_FOUND));
    }

    // devuelve lista con todas las citas ordenadas por hora 
    @GetMapping("/citas")
    List<Cita> readAll() {
        List<Cita> citas = (List<Cita>) citaRepository.findAll();
        return ordenarCitasPorHora(citas);
    }

    // devuelve lista con todas las citas de un médico ordenadas por hora 
    @GetMapping("/citas/medicos/{id}")
    List<Cita> readMedico(@PathVariable String id) {
        List<Cita> citas = (List<Cita>) citaRepository.findAllByMedico(id);
        return ordenarCitasPorHora(citas);
    }

    //método auxliar para ordenar las citas por hora
    private List<Cita> ordenarCitasPorHora(List<Cita> citas) {
        Comparator<Cita> comparador = new Comparator<Cita>() {
            @Override
            public int compare(Cita cita1, Cita cita2) {
                LocalTime hora1 = cita1.getHora();
                LocalTime hora2 = cita2.getHora();
                return hora1.compareTo(hora2);
            }
        };
        citas.sort(comparador);
        return citas;
    }

    // ACTUALIZAR

    // actualiza la información de una cita 
    @PutMapping("/citas/{id}")
    ResponseEntity<Cita> update(@PathVariable Long id, @RequestBody Cita newCita) {
        return citaRepository.findById(id).map(cita -> {
            cita.setFecha(newCita.getFecha());
            cita.setHora(newCita.getHora());
            cita.setPaciente(newCita.getPaciente());
            cita.setMedico(newCita.getMedico());
            citaRepository.save(cita);
            return ResponseEntity.ok().body(cita);
        }).orElse(new ResponseEntity<Cita>(HttpStatus.NOT_FOUND));
    }

    // BORRAR

    // borra una cita concreta
    @DeleteMapping("/citas/{id}")
    ResponseEntity<Cita> delete(@PathVariable Long id) {
        citaRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
