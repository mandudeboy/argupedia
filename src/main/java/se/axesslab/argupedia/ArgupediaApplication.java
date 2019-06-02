package se.axesslab.argupedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class ArgupediaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ArgupediaApplication.class, args);
	}
}
