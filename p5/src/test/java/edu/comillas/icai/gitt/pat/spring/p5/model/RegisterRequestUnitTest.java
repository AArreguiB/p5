package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        //Given
        RegisterRequest registro = new RegisterRequest("Ana","email.com",Role.USER,"aaaaaaA1");
        //When
        Set<ConstraintViolation<RegisterRequest>> violations=validator.validate(registro);
        //Then
        assertEquals( 1, violations.size());
        List<ConstraintViolation<RegisterRequest>> errores=new ArrayList<>(violations);
        assertEquals("email",errores.get(0).getPropertyPath().toString());
        assertEquals( "email.com",errores.get(0).getInvalidValue());

    }
    @Test
    public void testInvalidPassword() {
        //Given
        RegisterRequest registro = new RegisterRequest("Ana","email@com",Role.USER,"aaaaaa1");
        //when
        Set<ConstraintViolation<RegisterRequest>> violations=validator.validate(registro);
        //Then
        assertEquals( 1, violations.size());
        List<ConstraintViolation<RegisterRequest>> errores=new ArrayList<>(violations);
        assertEquals("password",errores.get(0).getPropertyPath().toString());
        assertEquals( "aaaaaa1",errores.get(0).getInvalidValue());
    }


}