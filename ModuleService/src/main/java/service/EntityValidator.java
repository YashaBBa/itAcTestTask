package service;

import service.impl.ValidationResult;

public interface EntityValidator<T> {
    ValidationResult validate(T entity);

}
