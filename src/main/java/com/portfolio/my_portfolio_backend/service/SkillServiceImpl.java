package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;

@Service
public class SkillServiceImpl implements ISkillService {
    
    private final ISkillRepository skillRepository;
    private final Validator validator;


    public SkillServiceImpl(ISkillRepository skillRepository, Validator validator) {
        this.skillRepository = skillRepository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Skill save(Skill skill) {
        BindingResult result = new BeanPropertyBindingResult(skill, "skill");
        validator.validate(skill, result);

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return skillRepository.save(skill);
    }   

    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        return skillRepository.findByPersonalInfoId(personalInfoId);
    }
}
