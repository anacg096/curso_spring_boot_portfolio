package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.model.Experience;

public interface IExperienceService {
    List<Experience> findAll();
    Optional<Experience> findById(Long id);
    Experience save(Experience experience);
    void deleteById(Long id);
    List<Experience> findExperienceByPersonalInfoId(Long personalInfoId);
}
