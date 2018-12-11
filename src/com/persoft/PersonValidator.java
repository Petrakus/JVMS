package com.persoft;

import com.persoft.validation.DefaultValidator;
import com.persoft.validation.GenericValidationResult;

public class PersonValidator implements DefaultValidator {

    public boolean validate(Person person) {
        GenericValidationResult validationResult = isEmail().forParam(person.getEmail());
        return validationResult.isValid();
    }
}
