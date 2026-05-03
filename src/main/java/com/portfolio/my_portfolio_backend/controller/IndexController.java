package com.portfolio.my_portfolio_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.my_portfolio_backend.service.IEducationService;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import com.portfolio.my_portfolio_backend.service.IProjectService;
import com.portfolio.my_portfolio_backend.service.ISkillService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final IPersonalInfoService personalInfoService;
    private final IEducationService educationService;
    private final ISkillService skillService;
    private final IExperienceService experienceService;
    private final IProjectService projectService;

    @GetMapping("/")
    public String showIndex(Model model) {
        System.out.println("Mostrando la página de inicio");

        model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
        model.addAttribute("experienceList", experienceService.findAll());
        model.addAttribute("educationList", educationService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("projectsList", projectService.findAll());

        return "index";
    }
}
