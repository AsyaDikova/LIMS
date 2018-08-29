package adk.lims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("adk.lims")
@SpringBootApplication
@EnableScheduling
public class LimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimsApplication.class, args);
	}
}
