package es.upm.dit.isst.medconapi.repository;

import org.springframework.data.repository.CrudRepository;
import es.upm.dit.isst.medconapi.model.Medico;
import java.util.*;

public interface MedicoRepository extends CrudRepository<Medico, String> {
    List<Medico> findByEspecialidad(String especialidad);
}
