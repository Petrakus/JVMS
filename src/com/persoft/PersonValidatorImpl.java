package com.persoft;

import com.persoft.validation.ValidatorUtil;


public class PersonValidatorImpl extends ValidatorUtil implements PersonValidator {
    @Override
    public void validate(Person person) {

        isEmail().validate(person.getEmail()).throwIfInvalid("Not valid email");

        notNull()
                .and(notEmpty())
                .and(length(2,5))
                .validate(person.getFirstName())
                .throwIfInvalid("Pleace specify valid firstName!");

        notNull().and(lessThanOrEqual(28)).validate(person.getAge()).throwIfInvalid("Age is not valid");


        must(age -> (Integer)age < 20).validate(person.getAge()).throwIfInvalid("Age is not valid by the predicate definition");

        if (person.isAdmin())
            notNull()
                    .and(notEmpty())
                    .and(isEqual("Petar"))
                    .validate(person.getFirstName())
                    .throwIfInvalid("Admin firstName is not valid.");
    }
}
