package com.persoft;

import com.persoft.validation.ValidatorUtil;

import javax.xml.bind.ValidationException;

public class PersonValidatorWithException implements ValidatorUtil {

    public void validate(Person person) throws ValidationException {

        isEmail().validate(person.getEmail()).throwIfInvalid("Not valid email");

        notBlank()
                .and(length(2, 5))
                .validate(person.getFirstName())
                .throwIfInvalid("Please specify valid firstName!");

        notNull().and(lessThanOrEqual(28)).validate(person.getAge()).throwIfInvalid("Age is not valid");


        must(age -> (Integer) age < 20).validate(person.getAge()).throwIfInvalid("Age is not valid by the predicate definition");

    }
}
