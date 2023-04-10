package es.upm.dit.isst.medconapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
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

import es.upm.dit.isst.medconapi.repository.MedicoRepository;
import es.upm.dit.isst.medconapi.model.Medico;

@RestController
public class MedicoController {
    private final MedicoRepository medicoRepository;
    public static final Logger log = LoggerFactory.getLogger(MedicoController.class);
    
    public MedicoController(MedicoRepository t) {
        this.medicoRepository = t;
    }
    
    // CREAR

    //crea un nuevo médico
    @PostMapping("/medicos")
    ResponseEntity<Medico> create(@RequestBody Medico newMedico) throws URISyntaxException {
      Medico result = medicoRepository.save(newMedico);
      return ResponseEntity.created(new URI("/medicos/" + result.getNcolegiado())).body(result);
    }

    //LEER

    //devuelve un médico concreto 
    @GetMapping("/medicos/{id}")
    ResponseEntity<Medico> read(@PathVariable String id) {
        return medicoRepository.findById(id).map(medico ->
         ResponseEntity.ok().body(medico)
      ).orElse(new ResponseEntity<Medico>(HttpStatus.NOT_FOUND));
    }
    
    //devuelve lista con todos los médicos
    @GetMapping("/medicos")
    List<Medico> readAll() {
        return (List<Medico>) medicoRepository.findAll();
    }

    //devuelve lista con todos los médicos de una especialidad 
    @GetMapping("/medicos/especialidad/{id}")
    List<Medico> readEspecialidad(@PathVariable String id) {
      return (List<Medico>) medicoRepository.findByEspecialidad(id);
    }
    
    //ACTUALIZAR
    
    //actualiza la información de un médico 
    @PutMapping("/medicos/{id}")
    ResponseEntity<Medico> update(@PathVariable String id, @RequestBody Medico newMedico) {
        return medicoRepository.findById(id).map(medico -> {
            medico.setNombre(newMedico.getNombre());
            medico.setApellidos(newMedico.getApellidos());
            medico.setEspecialidad(newMedico.getEspecialidad());
            medicoRepository.save(medico);
            return ResponseEntity.ok().body(medico);
          }).orElse(new ResponseEntity<Medico>(HttpStatus.NOT_FOUND));
    }

    //BORRAR
    
    //borra un médico concreto 
    @DeleteMapping("/medicos/{id}")
    ResponseEntity<Medico> delete(@PathVariable String id) {
      medicoRepository.deleteById(id);
      return ResponseEntity.ok().body(null);
    }
}
