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

import es.upm.dit.isst.medconapi.model.Consulta;
import es.upm.dit.isst.medconapi.repository.ConsultaRepository;

@RestController
public class ConsultaController {
    private final ConsultaRepository consultaRepository;
    public static final Logger log = LoggerFactory.getLogger(ConsultaController.class);

    public ConsultaController(ConsultaRepository t) {
                this.consultaRepository = t;
        }

    // CREAR

    // crea una consulta
    @PostMapping("/consultas")
    ResponseEntity<Consulta> create(@RequestBody Consulta newConsulta) throws URISyntaxException {
        Consulta result = consultaRepository.save(newConsulta);
        return ResponseEntity.created(new URI("/consultas/" + result.getId())).body(result);
    }

    // LEER

    // devuelve una consulta concreta
    @GetMapping("/consultas/{id}")
    ResponseEntity<Consulta> read(@PathVariable Long id) {
        return consultaRepository.findById(id).map(consulta -> ResponseEntity.ok().body(consulta))
                .orElse(new ResponseEntity<Consulta>(HttpStatus.NOT_FOUND));
    }

    // devuelve lista con todas las consultas
    @GetMapping("/consultas")
    List<Consulta> readAll() {
        return (List<Consulta>) consultaRepository.findAll();  
    }

    // devuelve lista con todas las consultas de un médico ordenadas por hora
    @GetMapping("/consultas/medicos/{id}")
    List<Consulta> readMedico(@PathVariable String id) {
        List<Consulta> consultas = (List<Consulta>) consultaRepository.findAllByMedico(id);
        return ordenarConsultasPorHora(consultas);
    }

    // devuelve lista con todas las consultas en un determinado estado 
    @GetMapping("/consultas/status/{id}")
    List<Consulta> readStatus(@PathVariable Integer id) {
        return (List<Consulta>) consultaRepository.findByStatus(id);  
    }

    // devuelve lista con todas las consultas de una sala de espera en un estado 
    @GetMapping("/consultas/sala/{idEspera}/{status}")
    List<Consulta> readSala(@PathVariable Integer idEspera, @PathVariable Integer status) {
        return (List<Consulta>) consultaRepository.findBySalaEsperaAndStatus(idEspera, status);  
    }

    // devuelve la consulta de un paciente 
    @GetMapping("/consultas/pacientes/{id}")
    Consulta readPaciente(@PathVariable String id) {
      return (Consulta) consultaRepository.findByPaciente(id);
    }

    //método auxliar para ordenar las consultas por hora
    private List<Consulta> ordenarConsultasPorHora(List<Consulta> consultas) {
        Comparator<Consulta> comparador = new Comparator<Consulta>() {
            @Override
            public int compare(Consulta consulta1, Consulta consulta2) {
                LocalTime hora1 = consulta1.getCita().getHora();
                LocalTime hora2 = consulta2.getCita().getHora();
                return hora1.compareTo(hora2);
            }
        };
        consultas.sort(comparador);
        return consultas;
    }


    // ACTUALIZAR

    // actualiza la información de una consulta
    @PutMapping("/consultas/{id}")
    ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody Consulta newConsulta) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setStatus(newConsulta.getStatus());
            consulta.setDuracion(newConsulta.getDuracion());
            consulta.setSalaEspera(newConsulta.getSalaEspera());
            consulta.setSalaConsulta(newConsulta.getSalaConsulta());
            consulta.setPaciente(newConsulta.getPaciente());
            consulta.setMedico(newConsulta.getMedico());
            consulta.setCita(newConsulta.getCita());
            consultaRepository.save(consulta);
            return ResponseEntity.ok().body(consulta);
        }).orElse(new ResponseEntity<Consulta>(HttpStatus.NOT_FOUND));
    }
    
    // actualiza el estado de la consulta 
    @PostMapping("/consultas/{id}/incrementa")
    ResponseEntity<Consulta> incrementa(@PathVariable Long id) {
      return consultaRepository.findById(id).map(consulta -> {
        consulta.setStatus(consulta.getStatus() + 1);
        consultaRepository.save(consulta);
        return ResponseEntity.ok().body(consulta);
      }).orElse(new ResponseEntity<Consulta>(HttpStatus.NOT_FOUND));  
    }
    
    // pone el status a 2 y finaliza la consulta
    @PostMapping("/consultas/{id}/finaliza")
    ResponseEntity<Consulta> finaliza(@PathVariable Long id) {
      return consultaRepository.findById(id).map(consulta -> {
        consulta.setStatus(2);
        consultaRepository.save(consulta);
        return ResponseEntity.ok().body(consulta);
      }).orElse(new ResponseEntity<Consulta>(HttpStatus.NOT_FOUND));  
    }

    // BORRAR

    // borra una consulta concreta
    @DeleteMapping("/consultas/{id}")
    ResponseEntity<Consulta> delete(@PathVariable Long id) {
        consultaRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
    
}
