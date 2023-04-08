package es.upm.dit.isst.medconapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import es.upm.dit.isst.medconapi.model.Paciente;
import es.upm.dit.isst.medconapi.model.Consulta;
import java.util.*;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {
    @Query("SELECT c FROM Consulta c WHERE c.medico.nColegiado = :idMedico")
    List<Consulta> findAllByMedico(@Param("idMedico") String idMedico);
    List<Consulta> findByStatus(Integer status);
    // este no se usa de momento 
    List<Consulta> findByPaciente(Paciente paciente);
}
