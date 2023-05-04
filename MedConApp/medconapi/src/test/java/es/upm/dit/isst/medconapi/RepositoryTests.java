package es.upm.dit.isst.medconapi;


import static org.junit.jupiter.api.Assertions.*;

import es.upm.dit.isst.medconapi.model.Cita;
import es.upm.dit.isst.medconapi.model.Consulta;
import es.upm.dit.isst.medconapi.model.Medico;
import es.upm.dit.isst.medconapi.model.Paciente;
import es.upm.dit.isst.medconapi.repository.CitaRepository;
import es.upm.dit.isst.medconapi.repository.ConsultaRepository;
import es.upm.dit.isst.medconapi.repository.MedicoRepository;
import es.upm.dit.isst.medconapi.repository.PacienteRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootTest
public class RepositoryTests {

    @Autowired
    private CitaRepository citaRepository;
	private ConsultaRepository consultaRepository;
	private MedicoRepository medicoRepository;
	private PacienteRepository pacienteRepository;


    @Test
    public void testCitaRepository() {

		
        Medico medico = new Medico();
        medico.setNcolegiado("12345");
        medico.setEspecialidad("Cardiología");
        medicoRepository.save(medico);

        Paciente paciente = new Paciente();
        paciente.setCipa("98765");
        paciente.setNombre("Paciente Test");
        pacienteRepository.save(paciente);

        
        Cita cita = new Cita();
        cita.setMedico(medico);
        cita.setPaciente(paciente);
        citaRepository.save(cita);

        
        List<Cita> citasPorMedico = citaRepository.findAllByMedico("12345");
        assertEquals(1, citasPorMedico.size());
        assertEquals(cita.getId(), citasPorMedico.get(0).getId());

        Cita citaPorPaciente = citaRepository.findByPaciente("98765");
        assertEquals(cita.getId(), citaPorPaciente.getId());
    }

    @Test
    public void testConsultaRepository() {
        Medico medico = new Medico();
        medico.setNcolegiado("67890");
        medico.setEspecialidad("Neurología");
        medicoRepository.save(medico);

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setStatus(1);
        consultaRepository.save(consulta);

        List<Consulta> consultasPorMedico = consultaRepository.findAllByMedico("67890");
        assertEquals(1, consultasPorMedico.size());
        assertEquals(consulta.getId(), consultasPorMedico.get(0).getId());

        List<Consulta> consultasPorStatus = consultaRepository.findByStatus(1);
        assertEquals(1, consultasPorStatus.size());
        assertEquals(consulta.getId(), consultasPorStatus.get(0).getId());
    }

    @Test
    public void testMedicoRepository() {
        Medico medico = new Medico();
        medico.setNcolegiado("13579");
        medico.setEspecialidad("Dermatología");
        medicoRepository.save(medico);

        List<Medico> medicosPorEspecialidad = medicoRepository.findByEspecialidad("Dermatología");
        assertEquals(1, medicosPorEspecialidad.size());
        assertEquals("13579", medicosPorEspecialidad.get(0).getNcolegiado());
    }

    @Test
    public void testPacienteRepository() {
        Paciente paciente = new Paciente();
        paciente.setCipa("24680");
        paciente.setNombre("Paciente Prueba");
        pacienteRepository.save(paciente);

        Paciente pacienteEncontrado = pacienteRepository.findById("24680").orElse(null);
        assertNotNull(pacienteEncontrado);
        assertEquals(paciente.getNombre(), pacienteEncontrado.getNombre());
    }
}






