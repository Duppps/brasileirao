package com.lpiii.trabFinal;

import com.lpiii.trabFinal.Entities.Match;
import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.MatchRepository;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

@SpringBootApplication
public class TrabFinalApplication implements CommandLineRunner {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MatchRepository matchRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrabFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Team gremio = new Team();
		gremio.setName("Grêmio");
		gremio.setCity("Porto Alegre");
		gremio.setStadium("Arena");

		Team inter = new Team();
		inter.setName("Internacional");
		inter.setCity("Porto Alegre");
		inter.setStadium("Beira-Rio");

		teamRepository.save(gremio);
		teamRepository.save(inter);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
