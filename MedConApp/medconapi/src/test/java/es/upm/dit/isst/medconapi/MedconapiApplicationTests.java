package es.upm.dit.isst.medconapi;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import es.upm.dit.isst.medconapi.repository.CitaRepository;
import es.upm.dit.isst.medconapi.repository.ConsultaRepository;
import es.upm.dit.isst.medconapi.repository.MedicoRepository;
import es.upm.dit.isst.medconapi.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootTest
class MedconapiApplicationTests {

	@Autowired
    private CitaRepository cita_repo;
	private ConsultaRepository consulta_repo;
	private MedicoRepository medico_repo;
	private PacienteRepository paciente_repo;

	@Test
	void contextLoads() {
	}

}