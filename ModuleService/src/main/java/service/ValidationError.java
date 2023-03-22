package service;


import java.util.Objects;

public class ValidationError {
    private String fieldIdentifier;
    private String errorMessage;

    public ValidationError(String fieldIdentifier, String errorMessage) {
        this.fieldIdentifier = fieldIdentifier;
        this.errorMessage = errorMessage;
    }

    public ValidationError() {
    }

    public String getFieldIdentifier() {
        return fieldIdentifier;
    }

    public void setFieldIdentifier(String fieldIdentifier) {
        this.fieldIdentifier = fieldIdentifier;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError that = (ValidationError) o;
        return Objects.equals(fieldIdentifier, that.fieldIdentifier) && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldIdentifier, errorMessage);
    }
}

