package ibf2022.paf.assessment.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.paf.assessment.server.repositories.UserRepository;

@SpringBootApplication
public class ServerApplication{

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}