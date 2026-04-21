package com.portfolio.my_portfolio_backend.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    private final IEducationService educationService;

    public EducationController(IEducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> findAll() {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Education> findById(@PathVariable Long id) {
        return educationService.findById(id);
    }

    @PostMapping
    public Education save(@RequestBody Education education) {
        return educationService.save(education);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education) {
        education.setId(id);
        return educationService.save(education);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        educationService.deleteById(id);
    }

}
