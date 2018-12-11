package com.persoft;

import com.persoft.validation.DefaultValidator;

import javax.xml.bind.ValidationException;

public class PersonValidatorWithException implements DefaultValidator {

    public void validate(Person person) throws ValidationException {

        isEmail().forParam(person.getEmail()).throwIfInvalid("Not a valid email");

        notNull()
                .and(notEmpty())
                .and(notEqual("foo"))
                .forParam(person.getFirstName())
                .throwIfInvalid("Please specify valid firstName!");

        notNull().and(lessThanOrEqual(28)).forParam(person.getAge()).throwIfInvalid("Age is not valid");


        must(age -> (Integer) age < 20).forParam(person.getAge()).throwIfInvalid("Age is not valid by the predicate definition");

    }
}
