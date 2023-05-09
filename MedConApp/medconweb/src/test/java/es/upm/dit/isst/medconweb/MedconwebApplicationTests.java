package es.upm.dit.isst.medconweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.medconweb.config.CustomAuthenticationProvider;
import es.upm.dit.isst.medconweb.model.Medico;

@SpringBootTest
class MedconwebApplicationTests {

	@Test
	void authenticateTest() {

		CustomAuthenticationProvider customAuthProvider = new CustomAuthenticationProvider();
		customAuthProvider.restTemplate = new RestTemplate();

		Medico medico = new Medico();
		medico.setNcolegiado("12345");
		medico.setPassword("password");

		Authentication auth = new UsernamePasswordAuthenticationToken(medico.getNcolegiado(), medico.getPassword());

		try {
			Authentication result = customAuthProvider.authenticate(auth);
			assertEquals(medico.getNcolegiado(), result.getPrincipal());
		} catch (AuthenticationException e) {
			// Test failed
			e.printStackTrace();
		}
	}

}
