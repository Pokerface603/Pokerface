package pokerface.pokerface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PokerfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokerfaceApplication.class, args);
	}

}
