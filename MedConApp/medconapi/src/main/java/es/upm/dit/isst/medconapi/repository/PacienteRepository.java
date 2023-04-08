package es.upm.dit.isst.medconapi.repository;

import org.springframework.data.repository.CrudRepository;
import es.upm.dit.isst.medconapi.model.Paciente;
import java.util.*;

public interface PacienteRepository extends CrudRepository<Paciente, String> {
    //de moemento no hacen falta (borrar al final si no se usan)
    List<Paciente> findByPresente(Boolean presente);
    Paciente findByIdEspera(String idEspera);
}

