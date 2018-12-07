package com.persoft.validation;

import javax.xml.bind.ValidationException;
import java.util.Optional;

public class GenericValidationResult {
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public static GenericValidationResult ok() {
        return new GenericValidationResult(true);
    }

    private GenericValidationResult(boolean valid) {
        this.valid = valid;
    }

    public static GenericValidationResult fail() {
        return new GenericValidationResult(false);
    }

    public Optional<String> getFieldNameIfInvalid(String field) {
        return this.valid ? Optional.empty() : Optional.of(field);
    }

    public void throwIfInvalid(String field) throws ValidationException{
        if (!this.valid) throw new ValidationException(field);
    }
}
