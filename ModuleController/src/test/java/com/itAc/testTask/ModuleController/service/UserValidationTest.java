package com.itAc.testTask.ModuleController.service;

import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.EntityValidator;
import service.ValidationError;
import service.impl.UserValidation;
import service.impl.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class UserValidationTest {
    private EntityValidator<User> userValidator;
    @Mock
    private ValidationResult validationResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userValidator = new UserValidation();
    }

    @Test
    public void testUserValidationWithValidUser() {
        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setPatronymic("Smith");
        user.setEmail("john.doe@example.com");



        ValidationResult result = userValidator.validate(user);

        Assertions.assertFalse(result.hasErrors());
        Assertions.assertEquals(0, result.getErrors().size());
    }

    @Test
    public void testUserValidationWithInvalidUser() {
        User user = new User();
        user.setName("John1");
        user.setSurname("Doe2");
        user.setPatronymic("Smith3");
        user.setEmail("johndoeexample.com");

        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("NameErr", "Invalid name, it should contain only latin characters and be less then 50"));
        errors.add(new ValidationError("SurnameErr", "Invalid surname, it should contain only latin characters"));
        errors.add(new ValidationError("PatronymicErr", "Invalid surname, it should contain only latin characters"));
        errors.add(new ValidationError("EmailErr", "Invalid email format"));

        ValidationResult validationResult = new ValidationResult();
        validationResult.setErrors(errors);



        ValidationResult result = userValidator.validate(user);

        Assertions.assertTrue(result.hasErrors());
        Assertions.assertEquals(errors, result.getErrors());
    }
}