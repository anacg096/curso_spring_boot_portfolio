package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;

    @NotBlank(message = "El nombre de la habilidad no puede estar vacío")
    private String name;

    @NotNull(message = "El porcentaje no puede ser nulo")
    @Min(value = 0, message = "El porcentaje de la habilidad debe ser un entero entre 0 y 100")
    @Max(value = 100, message = "El porcentaje de la habilidad debe ser un entero entre 0 y 100")
    private Integer levelPercentage;

    @NotBlank(message = "El icono de la habilidad no puede estar vacío")
    private String iconClass;
    
    private Long personalInfoId;
}