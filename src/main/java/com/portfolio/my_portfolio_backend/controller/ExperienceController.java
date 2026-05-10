package com.portfolio.my_portfolio_backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.mapper.ExperienceMapper;
import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/experiences")
public class ExperienceController {
    private final IExperienceService experienceService;

    @GetMapping
    public String listExperiences(Model model) {
        List<Experience> experienceList = experienceService.findAll();
        List<ExperienceDto> experienceDtos = experienceList.stream()
                .map(ExperienceMapper::toDto)
                .toList();
        model.addAttribute("experienceList", experienceList);
        return "experiences/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ExperienceDto newExperienceDto = new ExperienceDto();
        newExperienceDto.setStartDate(LocalDate.now());
        model.addAttribute("experienceDto", newExperienceDto);
        return "experiences/form-experience";
    }

    @PostMapping("/save")
    public String saveExperience(@Valid @ModelAttribute("experienceDto") ExperienceDto experienceDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "experience/form-experience";
        }

        try {
            Experience experience = ExperienceMapper.toEntity(experienceDto);
            experienceService.save(experience);
            return "redirect:/experience";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la experiencia laboral: " + e.getMessage());
            return "redirect:/experience";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Experience> experienceOptional = experienceService.findById(id);
        if (experienceOptional.isPresent()) {
            ExperienceDto experienceDto = ExperienceMapper.toDto(experienceOptional.get());
            model.addAttribute("experienceDto", experienceDto);
            return "experience/form-experience";
        } else {
            model.addAttribute("errorMessage", "Experiencia laboral no encontrada con ID: " + id);
            return "experience/form-experience";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listExperiencesByPersonalInfoId(@PathVariable Long personalInfoId, Model model){
        List<Experience> experiencesList = experienceService.findExperienceByPersonalInfoId(personalInfoId);

        List<ExperienceDto> experienceDtos = experiencesList.stream().map(ExperienceMapper::toDto).collect(Collectors.toList());
        model.addAttribute("experienceList", experienceDtos);
        return "experience/list-experience";
    }

    @PostMapping("/delete/{id}")
    public String deleteExperience(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            experienceService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Experiencia laboral eliminada con éxito de tu portfolio!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la experiencia laboral: " + e.getMessage());
        }
        return "redirect:/experience";
    }

}
