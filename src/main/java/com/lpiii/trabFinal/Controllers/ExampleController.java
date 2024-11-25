package com.lpiii.trabFinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {

    @GetMapping("/example")
    public String examplePage(Model model) {
        model.addAttribute("title", "Página de Exemplo");
        model.addAttribute("message", "Bem-vindo ao exemplo de Spring MVC com Thymeleaf!");
        return "example";
    }
}
