package com.lpiii.trabFinal.Controllers;

import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/team")
    public String getTeams(Model model) {
        List<Team> teamList = teamRepository.findAll();
        model.addAttribute("teamList", teamList);
        return "pages/team";
    }

    @PostMapping("/team")
    public ResponseEntity<String> createTeam(@RequestBody Team team) {
        teamRepository.save(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
