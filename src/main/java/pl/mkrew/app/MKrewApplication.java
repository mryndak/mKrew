package pl.mkrew.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.mkrew.app.service.parser.RCKIKKrakowParser;

@SpringBootApplication
public class MKrewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MKrewApplication.class, args);
	}

}
