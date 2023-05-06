package es.upm.dit.isst.medconapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import es.upm.dit.isst.medconapi.model.Consulta;
import java.util.*;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {
    @Query("SELECT c FROM Consulta c WHERE c.medico.ncolegiado = :idMedico")
    List<Consulta> findAllByMedico(@Param("idMedico") String idMedico);
    @Query("SELECT c FROM Consulta c WHERE c.paciente.cipa = :idPaciente")
    Consulta findByPaciente(@Param("idPaciente") String idPaciente);
    List<Consulta> findByStatus(Integer status);
    List<Consulta> findBySalaEsperaAndStatus(Integer salaEspera, Integer status);
}
