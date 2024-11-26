package com.lpiii.trabFinal;

import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabFinalApplication implements CommandLineRunner {

	@Autowired
	private TeamRepository teamRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrabFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Team gremio = new Team();
		gremio.setName("Grêmio");
		teamRepository.save(gremio);

		System.out.println("Team saved: " + gremio.getName());
	}
}
