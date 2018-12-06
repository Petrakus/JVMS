package com.persoft;

import com.persoft.validation.ValidatorUtil;


public class PersonValidatorImpl implements PersonValidator {
    @Override
    public void validate(Person person) {

        ValidatorUtil.isEmail().validate(person.getEmail()).throwIfInvalid("Not valid email");

        ValidatorUtil.notNull()
                .and(ValidatorUtil.notEmpty())
                .and(ValidatorUtil.length(2,5))
                .validate(person.getFirstName())
                .throwIfInvalid("Pleace specify valid firstName!");

        ValidatorUtil.notNull().and(ValidatorUtil.lessThanOrEqual(28)).validate(person.getAge()).throwIfInvalid("Age is not valid");


        ValidatorUtil.must(age -> (Integer)age < 20).validate(person.getAge()).throwIfInvalid("Age is not valid by the predicate definition");

        if (person.isAdmin())
            ValidatorUtil.notNull()
                    .and(ValidatorUtil.notEmpty())
                    .and(ValidatorUtil.isEqual("Petar"))
                    .validate(person.getFirstName())
                    .throwIfInvalid("Admin firstName is not valid.");
    }
}
