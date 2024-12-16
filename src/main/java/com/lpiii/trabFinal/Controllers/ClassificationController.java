package com.lpiii.trabFinal.Controllers;

import com.lpiii.trabFinal.Entities.Team;
import com.lpiii.trabFinal.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassificationController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Team> teamList = teamRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));

        model.addAttribute("teamList", teamList);

        return "pages/home";
    }
}
