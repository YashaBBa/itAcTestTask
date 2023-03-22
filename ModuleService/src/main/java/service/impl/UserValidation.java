package service.impl;

import entity.User;
import org.springframework.context.support.BeanDefinitionDsl;
import service.EntityValidator;
import service.ValidationError;

import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public class UserValidation implements EntityValidator<User> {
    private static final String TEXT_PATTERN = "[A-Za-z]{2,50}";

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    public ValidationResult validate(User entity) {
        ValidationResult result = new ValidationResult();
        BiPredicate<String, String> validationPredicate = (pattern, valueToCheck) -> !Pattern.matches(pattern, valueToCheck);
        if (validationPredicate.test(TEXT_PATTERN, entity.getName())) {
            ValidationError error = new ValidationError();
            error.setFieldIdentifier("NameErr");
            error.setErrorMessage("Invalid name, it should contain only latin characters and be less then 50");
            result.getErrors().add(error);
        }
        if (validationPredicate.test(TEXT_PATTERN, entity.getSurname())) {
            ValidationError error = new ValidationError();
            error.setFieldIdentifier("SurnameErr");
            error.setErrorMessage("Invalid surname, it should contain only latin characters");
            result.getErrors().add(error);
        }
        if (validationPredicate.test(TEXT_PATTERN, entity.getPatronymic())) {
            ValidationError error = new ValidationError();
            error.setFieldIdentifier("PatronymicErr");
            error.setErrorMessage("Invalid surname, it should contain only latin characters");
            result.getErrors().add(error);
        }
        if (validationPredicate.test(EMAIL_PATTERN, entity.getEmail())) {
            ValidationError error = new ValidationError();
            error.setFieldIdentifier("EmailErr");
            error.setErrorMessage("Invalid email format");
            result.getErrors().add(error);
            System.out.println(BeanDefinitionDsl.Role.values().length);
        }
        return result;
    }
}
