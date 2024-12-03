package com.lpiii.trabFinal;

import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

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
		gremio.setCity("Porto Alegre");
		gremio.setStadium("Arena");
		teamRepository.save(gremio);

		System.out.println("Team saved: " + gremio.getName());
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
