package es.upm.dit.isst.medconapi;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import es.upm.dit.isst.medconapi.model.Cita;
import es.upm.dit.isst.medconapi.model.Consulta;
import es.upm.dit.isst.medconapi.model.Medico;
import es.upm.dit.isst.medconapi.model.Paciente;
import es.upm.dit.isst.medconapi.repository.CitaRepository;
import es.upm.dit.isst.medconapi.repository.ConsultaRepository;
import es.upm.dit.isst.medconapi.repository.MedicoRepository;
import es.upm.dit.isst.medconapi.repository.PacienteRepository;

@SpringBootApplication
public class MedconapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedconapiApplication.class, args);
	}
	
	@Component
	class dataLoader implements CommandLineRunner {
		@Autowired
		private PacienteRepository pacienteRepository;
		@Autowired
		private MedicoRepository medicoRepository;
		@Autowired
		private CitaRepository citaRepository;
		@Autowired
		private ConsultaRepository consultaRepository;

		@Override
		public void run(String... args) throws Exception {

			Paciente p2 = new Paciente();
			p2.setCipa("8475291432");
			p2.setNombre("Carlos");
			p2.setApellidos("Lopez");
			p2.setPresente(false);
			pacienteRepository.save(p2);

			Paciente p3 = new Paciente();
			p3.setCipa("7331089076");
			p3.setNombre("Ana");
			p3.setApellidos("Sanchez");
			p3.setPresente(false);
			pacienteRepository.save(p3);

			Paciente p4 = new Paciente();
			p4.setCipa("4192538479");
			p4.setNombre("Diego");
			p4.setApellidos("Torres");
			p4.setPresente(false);
			pacienteRepository.save(p4);

			Paciente p5 = new Paciente();
			p5.setCipa("5728135092");
			p5.setNombre("Maria");
			p5.setApellidos("Garcia");
			p5.setPresente(false);
			pacienteRepository.save(p5);

			Paciente p6 = new Paciente();
			p6.setCipa("9600342165");
			p6.setNombre("Jorge");
			p6.setApellidos("Fernandez");
			p6.setPresente(false);
			pacienteRepository.save(p6);

			Paciente p7 = new Paciente();
			p7.setCipa("2874068535");
			p7.setNombre("Carmen");
			p7.setApellidos("Ruiz");
			p7.setPresente(false);
			pacienteRepository.save(p7);

			Paciente p8 = new Paciente();
			p8.setCipa("4567895789");
			p8.setNombre("Javier");
			p8.setApellidos("Rodriguez");
			p8.setPresente(false);
			pacienteRepository.save(p8);

			Paciente p9 = new Paciente();
			p9.setCipa("6389561047");
			p9.setNombre("Sofia");
			p9.setApellidos("Perez");
			p9.setPresente(false);
			pacienteRepository.save(p9);

			Paciente p10 = new Paciente();
			p10.setCipa("6789011234");
			p10.setNombre("Guillermo");
			p10.setApellidos("Jimenez");
			p10.setPresente(false);
			pacienteRepository.save(p10);

			Medico m1 = new Medico();
			m1.setNcolegiado("12345");
			m1.setNombre("Laura");
			m1.setApellidos("Robles");
			m1.setPassword("laura1");
			m1.setEspecialidad("Ginecologia");
			medicoRepository.save(m1);

			Medico m2 = new Medico();
			m2.setNcolegiado("67890");
			m2.setNombre("Juan");
			m2.setApellidos("Lopez");
			m2.setPassword("juan1");
			m2.setEspecialidad("Cardiologia");
			medicoRepository.save(m2);

			Medico m3 = new Medico();
			m3.setNcolegiado("23456");
			m3.setNombre("Ana");
			m3.setApellidos("Martinez");
			m3.setPassword("ana1");
			m3.setEspecialidad("Pediatria");
			medicoRepository.save(m3);

			Cita c2 = new Cita();
			c2.setFecha(LocalDate.of(2023, 5, 10));
			c2.setHora(LocalTime.of(10, 15));
			c2.setPaciente(p2);
			c2.setMedico(m1);
			citaRepository.save(c2);

			Cita c3 = new Cita();
			c3.setFecha(LocalDate.of(2023, 5, 10));
			c3.setHora(LocalTime.of(10, 30));
			c3.setPaciente(p3);
			c3.setMedico(m1);
			citaRepository.save(c3);

			Cita c4 = new Cita();
			c4.setFecha(LocalDate.of(2023, 5, 10));
			c4.setHora(LocalTime.of(10, 45));
			c4.setPaciente(p4);
			c4.setMedico(m1);
			citaRepository.save(c4);

			Cita c5 = new Cita();
			c5.setFecha(LocalDate.of(2023, 5, 10));
			c5.setHora(LocalTime.of(10, 0));
			c5.setPaciente(p5);
			c5.setMedico(m2);
			citaRepository.save(c5);

			Cita c6 = new Cita();
			c6.setFecha(LocalDate.of(2023, 5, 10));
			c6.setHora(LocalTime.of(10, 15));
			c6.setPaciente(p6);
			c6.setMedico(m2);
			citaRepository.save(c6);

			Cita c7 = new Cita();
			c7.setFecha(LocalDate.of(2023, 5, 10));
			c7.setHora(LocalTime.of(10, 30));
			c7.setPaciente(p7);
			c7.setMedico(m2);
			citaRepository.save(c7);

			Cita c8 = new Cita();
			c8.setFecha(LocalDate.of(2023, 5, 10));
			c8.setHora(LocalTime.of(10, 15));
			c8.setPaciente(p8);
			c8.setMedico(m3);
			citaRepository.save(c8);

			Cita c9 = new Cita();
			c9.setFecha(LocalDate.of(2023, 5, 10));
			c9.setHora(LocalTime.of(10, 30));
			c9.setPaciente(p9);
			c9.setMedico(m3);
			citaRepository.save(c9);

			Cita c10 = new Cita();
			c10.setFecha(LocalDate.of(2023, 5, 10));
			c10.setHora(LocalTime.of(10, 45));
			c10.setPaciente(p10);
			c10.setMedico(m3);
			citaRepository.save(c10);

			Consulta consulta2 = new Consulta();
			consulta2.setStatus(0);
			consulta2.setSalaEspera(1);
			consulta2.setSalaConsulta(1);
			consulta2.setPaciente(p2);
			consulta2.setMedico(m1);
			consulta2.setCita(c2);
			consultaRepository.save(consulta2);

			Consulta consulta3 = new Consulta();
			consulta3.setStatus(0);
			consulta3.setSalaEspera(1);
			consulta3.setSalaConsulta(1);
			consulta3.setPaciente(p3);
			consulta3.setMedico(m1);
			consulta3.setCita(c3);
			consultaRepository.save(consulta3);

			Consulta consulta4 = new Consulta();
			consulta4.setStatus(0);
			consulta4.setSalaEspera(1);
			consulta4.setSalaConsulta(1);
			consulta4.setPaciente(p4);
			consulta4.setMedico(m1);
			consulta4.setCita(c4);
			consultaRepository.save(consulta4);

			Consulta consulta5 = new Consulta();
			consulta5.setStatus(0);
			consulta5.setSalaEspera(1);
			consulta5.setSalaConsulta(2);
			consulta5.setPaciente(p5);
			consulta5.setMedico(m2);
			consulta5.setCita(c5);
			consultaRepository.save(consulta5);

			Consulta consulta6 = new Consulta();
			consulta6.setStatus(0);
			consulta6.setSalaEspera(1);
			consulta6.setSalaConsulta(2);
			consulta6.setPaciente(p6);
			consulta6.setMedico(m2);
			consulta6.setCita(c6);
			consultaRepository.save(consulta6);

			Consulta consulta7 = new Consulta();
			consulta7.setStatus(0);
			consulta7.setSalaEspera(1);
			consulta7.setSalaConsulta(2);
			consulta7.setPaciente(p7);
			consulta7.setMedico(m2);
			consulta7.setCita(c7);
			consultaRepository.save(consulta7);

			Consulta consulta8 = new Consulta();
			consulta8.setStatus(0);
			consulta8.setSalaEspera(2);
			consulta8.setSalaConsulta(3);
			consulta8.setPaciente(p8);
			consulta8.setMedico(m3);
			consulta8.setCita(c8);
			consultaRepository.save(consulta8);

			Consulta consulta9 = new Consulta();
			consulta9.setStatus(0);
			consulta9.setSalaEspera(2);
			consulta9.setSalaConsulta(3);
			consulta9.setPaciente(p9);
			consulta9.setMedico(m3);
			consulta9.setCita(c9);
			consultaRepository.save(consulta9);

			Consulta consulta10 = new Consulta();
			consulta10.setStatus(0);
			consulta10.setSalaEspera(2);
			consulta10.setSalaConsulta(3);
			consulta10.setPaciente(p10);
			consulta10.setMedico(m3);
			consulta10.setCita(c10);
			consultaRepository.save(consulta10);
		}
	}
}
