package com.lpiii.trabFinal.Controllers;

import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/")
    public String getTeams(Model model) {
        List<Team> teamList = teamRepository.findAll();
        model.addAttribute("teamList", teamList);
        return "pages/team";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody Team teamUpdated) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Team not found with id " + id));

        team.setName(teamUpdated.getName());
        team.setCity(teamUpdated.getCity());
        team.setStadium(teamUpdated.getStadium());

        teamRepository.save(team);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createTeam(@RequestBody Team team) {
        teamRepository.save(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
