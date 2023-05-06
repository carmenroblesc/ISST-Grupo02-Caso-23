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
import org.springframework.boot.test.context.SpringBootTest;
<<<<<<< HEAD
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
=======


>>>>>>> 1ce547b98b89f576f3c3a7e27df5eec7e5c11097

@SpringBootTest
public class RepositoryTests {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @BeforeEach
    public void setUp() {
        // Limpia la base de datos antes de cada prueba, si es necesario
        consultaRepository.deleteAll();
        citaRepository.deleteAll();
        medicoRepository.deleteAll();
        pacienteRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        // Limpia la base de datos después de cada prueba, si es necesario
        consultaRepository.deleteAll();
        citaRepository.deleteAll();
        medicoRepository.deleteAll();
        pacienteRepository.deleteAll();
    }

    @Test
    public void testCitaRepository() {

        Medico medico = new Medico();
        medico.setNcolegiado("15234");
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

        List<Cita> citasPorMedico = citaRepository.findAllByMedico("15234");
        assertEquals(1, citasPorMedico.size());
        assertEquals(cita.getId(), citasPorMedico.get(0).getId());

        Cita citaPorPaciente = citaRepository.findByPaciente("98765");
        assertEquals(cita.getId(), citaPorPaciente.getId());
    }

    @Test
    public void testConsultaRepository() {
        Medico medico = new Medico();
        medico.setNcolegiado("67908");
        medico.setEspecialidad("Neurología");
        medicoRepository.save(medico);

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setStatus(1);
        consultaRepository.save(consulta);

        List<Consulta> consultasPorMedico = consultaRepository.findAllByMedico("67908");
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
