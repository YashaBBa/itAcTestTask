package service.impl;

import lombok.extern.log4j.Log4j2;
import service.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private List<ValidationError> errors = new ArrayList<>();

    public List<ValidationError> getErrors() {
        return errors;
    }
    public boolean hasErrors(){
        return !errors.isEmpty();
    }
    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
