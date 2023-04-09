package es.upm.dit.isst.medconapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

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

import es.upm.dit.isst.medconapi.repository.PacienteRepository;
import es.upm.dit.isst.medconapi.model.Paciente;

@RestController
public class PacienteController {
    private final PacienteRepository pacienteRepository;
    public static final Logger log = LoggerFactory.getLogger(PacienteController.class);
    
    public PacienteController(PacienteRepository t) {
        this.pacienteRepository = t;
    }
    // CREAR

    //crea un nuevo paciente
    @PostMapping("/pacientes")
    ResponseEntity<Paciente> create(@RequestBody Paciente newPaciente) throws URISyntaxException {
      Paciente result = pacienteRepository.save(newPaciente);
      return ResponseEntity.created(new URI("/pacientes/" + result.getCipa())).body(result);
    }

    //LEER

    //devuelve un paciente concreto 
    @GetMapping("/pacientes/{id}")
    ResponseEntity<Paciente> read(@PathVariable String id) {
        return pacienteRepository.findById(id).map(paciente ->
         ResponseEntity.ok().body(paciente)
      ).orElse(new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND));
    }
    
    //devuelve lista con todos los pacientes 
    @GetMapping("/pacientes")
    List<Paciente> readAll() {
        return (List<Paciente>) pacienteRepository.findAll();
    }
    
    //ACTUALIZAR
    
    //actualiza la información de un paciente 
    @PutMapping("/pacientes/{id}")
    ResponseEntity<Paciente> update(@PathVariable String id, @RequestBody Paciente newPaciente) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNombre(newPaciente.getNombre());
            paciente.setApellidos(newPaciente.getApellidos());
            paciente.setIdEspera(newPaciente.getIdEspera());
            paciente.setPresente(newPaciente.getPresente());
            pacienteRepository.save(paciente);
            return ResponseEntity.ok().body(paciente);
          }).orElse(new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND));
    }
    
    //actualiza el estado de un paciente (para registrar la llegada de un paciente)
    @PostMapping("/pacientes/{id}/registrar")
    ResponseEntity<Paciente> registrar(@PathVariable String id) {
      return pacienteRepository.findById(id).map(paciente -> {
        paciente.setPresente(true);
        pacienteRepository.save(paciente);
        return ResponseEntity.ok().body(paciente);
      }).orElse(new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND));  
    }
    
    //actualiza el identificador de espera de un paciente (se lo asigna)
    @PostMapping("/pacientes/{id}/asignarIdEspera")
    ResponseEntity<Paciente> asignarIdEspera(@PathVariable String id) {
      return pacienteRepository.findById(id).map(paciente -> {
        String idEspera = asignarIdentificador(paciente);
        paciente.setIdEspera(idEspera);
        pacienteRepository.save(paciente);
        return ResponseEntity.ok().body(paciente);
      }).orElse(new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND));  
    }
    
    //método auxiliar para asignar un identificador de espera a un paciente
    private String asignarIdentificador(Paciente paciente) {
        //generar número aleatorio entre 1000 y 9999
        Random random = new Random();
        int numeroAleatorio = random.nextInt(9000) + 1000;
        // Obtener las iniciales del paciente
        String iniciales = String.valueOf(paciente.getNombre().charAt(0)) + String.valueOf(paciente.getApellidos().charAt(0));
        // Concatenar las iniciales y el número aleatorio para crear el identificador de espera
        String idEspera = iniciales.toUpperCase() + "-" + String.valueOf(numeroAleatorio);
        return idEspera;
    }

    //BORRAR
    
    //borra un paciente concreto 
    @DeleteMapping("/pacientes/{id}")
    ResponseEntity<Paciente> delete(@PathVariable String id) {
      pacienteRepository.deleteById(id);
      return ResponseEntity.ok().body(null);
    }
}