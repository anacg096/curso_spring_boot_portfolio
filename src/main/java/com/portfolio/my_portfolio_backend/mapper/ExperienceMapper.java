package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.model.Experience;

public class ExperienceMapper {

    public static ExperienceDto toDto(Experience experience){
        if(experience==null){
            return null;
        }
        ExperienceDto experienceDto = new ExperienceDto();

        experienceDto.setId(experience.getId());
        experienceDto.setJobTitle(experience.getJobTitle());
        experienceDto.setCompanyName(experience.getCompanyName());
        experienceDto.setStartDate(experience.getStartDate());
        experienceDto.setEndDate(experience.getEndDate());
        experienceDto.setDescription(experience.getDescription());
        experienceDto.setPersonalInfoId(experience.getPersonalInfoId());

        return experienceDto;
    }

    public static Experience toEntity(ExperienceDto dto){
        if (dto == null) {
            return null;
        }
        Experience experience = new Experience();

        experience.setId(dto.getId());
        experience.setJobTitle(dto.getJobTitle());
        experience.setCompanyName(dto.getCompanyName());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setDescription(dto.getDescription());
        experience.setPersonalInfoId(dto.getPersonalInfoId());

        return experience;
    }

}
