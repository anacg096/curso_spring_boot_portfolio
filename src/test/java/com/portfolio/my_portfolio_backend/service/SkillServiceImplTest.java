package com.portfolio.my_portfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @Mock
    private ISkillRepository skillRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private SkillServiceImpl skillService;


    @Test
    void testFindAllReturnListOfSkills() {
        //Arrange
        List<Skill> mockSkills = Arrays.asList(new Skill(), new Skill());
        when(skillRepository.findAll()).thenReturn(mockSkills);

        //Act
        List<Skill> skills = skillService.findAll();

        //Assert
        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnSkillWhenFound() {
        Long id = 1L;
        Skill skillMock = new Skill();
        when(skillRepository.findById(id)).thenReturn(Optional.of(skillMock));

        Optional<Skill> skillOptional = skillService.findById(id);

        assertTrue(skillOptional.isPresent());
        assertEquals(skillMock, skillOptional.get());
        verify(skillRepository, times(1)).findById(id);
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid() {
        Skill invalidSkill = new Skill();

        doAnswer(InvocationOnMock -> {
            BindingResult result = InvocationOnMock.getArgument(1);
            result.rejectValue("name", "NotBlank", "El nombre no puede estar vacío");
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill),
                "Debe lanzarse un ValidationException si el objeto Skill no es válido");

        verify(skillRepository, never()).save(any(Skill.class));
    }

    @Test
    void testSaveSkillSavesValidSkill() {
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));

        Skill savedSkill = skillService.save(validSkill);

        assertNotNull(savedSkill);
        verify(skillRepository, times(1)).save(validSkill);
    }

}
