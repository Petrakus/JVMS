package com.persoft.validation;

import javax.xml.bind.ValidationException;

public class ValidationResult {
    private boolean valid;
    private String message;

    public boolean isValid() {
        return valid;
    }

    static ValidationResult ok() {
        return new ValidationResult(true, null);
    }

    private ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    static ValidationResult fail(String message) {
        return new ValidationResult(false, message);
    }

    public void throwIfInvalid() throws ValidationException {
        if(!this.valid) throw new ValidationException(getMessage());
    }

    public void throwIfInvalid(String fieldName) throws ValidationException {
        if(!this.valid) throw new ValidationException(fieldName + " : " + getMessage());
    }

    public void throwIfInvalidCustomMessage(String message) throws ValidationException{
        if (!this.valid) throw new ValidationException(message);
    }

    public ValidationResult withMessage(String fieldName){
        if(!this.valid) {
            this.message = fieldName + " : " + getMessage();
        }
        return this;
    }

    public ValidationResult withCustomMessage(String message){
        if(!this.valid) {
            this.message = message;
        }
        return this;
    }

    public String getMessage() {
        return message;
    }

}
