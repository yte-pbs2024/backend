package yte.pbs2024;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class Pbs2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Pbs2024Application.class, args);
	}
}

