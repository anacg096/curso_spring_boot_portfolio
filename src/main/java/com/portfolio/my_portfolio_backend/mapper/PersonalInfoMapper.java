package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.PersonalInfoDto;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;

public class PersonalInfoMapper {

    public static PersonalInfoDto toDto(PersonalInfo personalInfo){
        if(personalInfo==null){
            return null;
        }
        PersonalInfoDto personalInfoDto = new PersonalInfoDto();

        personalInfoDto.setId(personalInfo.getId());
        personalInfoDto.setFirstName(personalInfo.getFirstName());
        personalInfoDto.setLastName(personalInfo.getLastName());
        personalInfoDto.setTitle(personalInfo.getTitle());
        personalInfoDto.setProfileDescription(personalInfo.getProfileDescription());
        personalInfoDto.setProfileImageUrl(personalInfo.getProfileImageUrl());
        personalInfoDto.setYearsOfExperience(personalInfo.getYearsOfExperience());
        personalInfoDto.setEmail(personalInfo.getEmail());
        personalInfoDto.setPhone(personalInfo.getPhone());
        personalInfoDto.setLinkedinUrl(personalInfo.getLinkedinUrl());
        personalInfoDto.setGithubUrl(personalInfo.getGithubUrl());

        return personalInfoDto;
    }

    public static PersonalInfo toEntity(PersonalInfoDto dto){
        if (dto == null) {
            return null;
        }
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setId(dto.getId());
        personalInfo.setFirstName(dto.getFirstName());
        personalInfo.setLastName(dto.getLastName());
        personalInfo.setTitle(dto.getTitle());
        personalInfo.setProfileDescription(dto.getProfileDescription());
        personalInfo.setProfileImageUrl(dto.getProfileImageUrl());
        personalInfo.setYearsOfExperience(dto.getYearsOfExperience());
        personalInfo.setEmail(dto.getEmail());
        personalInfo.setPhone(dto.getPhone());
        personalInfo.setLinkedinUrl(dto.getLinkedinUrl());
        personalInfo.setGithubUrl(dto.getGithubUrl());

        return personalInfo;
    }
    
}
