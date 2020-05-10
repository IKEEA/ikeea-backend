package mif.vu.ikeea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IkeeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkeeaApplication.class, args);
	}
}
