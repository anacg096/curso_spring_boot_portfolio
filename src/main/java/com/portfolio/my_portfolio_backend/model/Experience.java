package com.portfolio.my_portfolio_backend.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id;

    @NotBlank(message = "El título del trabajo no puede estar vacío")
    private String jobTitle;
    
    @NotBlank(message = "El nombre de la empresa no puede estar vacío")
    private String companyName;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    private LocalDate endDate;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;
    
    private Long personalInfoId;
    
}
