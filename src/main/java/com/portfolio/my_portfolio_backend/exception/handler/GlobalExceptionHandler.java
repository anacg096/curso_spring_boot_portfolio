package com.portfolio.my_portfolio_backend.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portfolio.my_portfolio_backend.exception.ValidationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        model.addAttribute("errorMessage", "Se encontraron errores de validación");

        return "error/validation";
    }
}
