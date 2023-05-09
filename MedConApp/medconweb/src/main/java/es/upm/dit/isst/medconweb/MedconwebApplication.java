package es.upm.dit.isst.medconweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedconwebApplication {
    public static final Logger log = LoggerFactory.getLogger(MedconwebApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MedconwebApplication.class, args);
	}
}
