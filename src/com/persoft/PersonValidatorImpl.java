package com.persoft;

import com.persoft.utils.ValidatorUtil;

public class PersonValidatorImpl implements PersonValidator {
    @Override
    public void validate(Person person) {

        ValidatorUtil.notNull()
                .and(ValidatorUtil.notEmpty())
                .and(ValidatorUtil.length(2,5))
                .validate(person.getFirstName())
                .throwIfInvalid("Pleace specify valid firstName!");

        ValidatorUtil.notNull().and(ValidatorUtil.lessThan(9)).validate(person.getAge()).throwIfInvalid("Age is not valid");
        ValidatorUtil.notNull().and(ValidatorUtil.lessThanOrEqual(19)).validate(person.getAge()).throwIfInvalid("Age is not valid");

        if (person.isAdmin())
            ValidatorUtil.notNull()
                    .and(ValidatorUtil.notEmpty())
                    .and(ValidatorUtil.equalCheck("Petar"))
                    .validate(person.getFirstName())
                    .throwIfInvalid("Admin firstName is not valid.");
    }
}
