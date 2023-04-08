package es.upm.dit.isst.medconapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import es.upm.dit.isst.medconapi.model.Cita;
import java.util.*;

public interface CitaRepository extends CrudRepository<Cita, Long> {
    @Query("SELECT c FROM Cita c WHERE c.medico.nColegiado = :idMedico")
    List<Cita> findAllByMedico(@Param("idMedico") String idMedico);
    @Query("SELECT c FROM Cita c WHERE c.paciente.cipa = :idPaciente")
    Cita findByPaciente(@Param("idPaciente") String idPaciente);
}
