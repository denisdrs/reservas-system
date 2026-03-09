package projeto.faculdade.reservas_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReservasSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasSystemApplication.class, args);
	}

}
