package com.portfolio.my_portfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Asegura que cada prueba se ejecute con un contexto limpio y la base de datos se reinicie antes de cada prueba
public class SkillServiceTest {
    @Autowired
    private ISkillService skillService;
    @Autowired
    private ISkillRepository skillRepository;

    //Lo que lombok genera
    // public SkillServiceTest(ISkillService skillService, ISkillRepository skillRepository) {
    //     this.skillService = skillService;
    //     this.skillRepository = skillRepository;
    // }

    // Lo que Spring espera para @SpringBootTest
    // public SkillServiceTest() {
    //     // Constructor vacío, luego Spring inyecta con @Autowired
    // }


    @Test
    void testSaveValidSkill(){
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        Skill savedSkill = skillService.save(validSkill);

        assertNotNull(savedSkill.getId(),"El objeto guardado debe tener un ID asignado");

        assertNotNull(skillRepository
                .findById(savedSkill.getId())
                .orElse(null), "El objeto guardado debe existir en la base de datos");
    }

    @Test
    void testSaveInvalidSkill() {
        Skill invalidSkill = new Skill(null, "", 90, "fab fa-java", 1L);

        assertThrows(ValidationException.class, () -> {
            skillService.save(invalidSkill);
        }, "Debe lanzarse una ValidationException al intentar guardar una habilidad con un nombre vacío");
    }
}
