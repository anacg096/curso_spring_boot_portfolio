package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;

    @NotBlank (message = "First name is required")
    private String firstName;
    @NotBlank (message = "Last name is required")
    private String lastName;
    @NotBlank (message = "Title is required")
    private String title;
    @NotBlank (message = "Profile description is required")
    private String profileDescription;
    @NotBlank (message = "Profile image URL is required")
    private String profileImageUrl;
    @Min(value = 0, message = "Years of experience must be a non-negative integer")
    private Integer yearsOfExperience;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank (message = "Phone number is required")
    private String phone;
    @NotBlank (message = "Location is required")
    private String linkedinUrl;
    @NotBlank (message = "Location is required")
    private String githubUrl;
}
