package com.persoft;

import com.persoft.validation.DefaultValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PersonValidatorWithErrorsList implements DefaultValidator {

    public List<Optional<String>> validate(Person person) {

        List<Optional<String>> errors = new ArrayList<>();

        errors.add(isEmail().validate(person.getEmail()).withMessage("Not valid email"));

        errors.add(notBlank()
                .and(length(2, 5))
                .validate(person.getFirstName())
                .withMessage("Please specify valid firstName!"));

        errors.add(notNull().and(lessThanOrEqual(28)).validate(person.getAge()).withMessage("Age is not valid"));


        errors.add(must(age -> (Integer) age < 20).validate(person.getAge()).withMessage("Age is not valid by the predicate definition"));

        return errors;
    }
}
