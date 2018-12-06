package com.persoft;

import com.persoft.utils.ValidatorUtil;

public class PersonValidatorImpl implements PersonValidator {
    @Override
    public void validate(Person person) {

        ValidatorUtil.notNull
                .and(ValidatorUtil.notEmpty)
                .and(ValidatorUtil.maxLength(5))
                .validate(person.getFirstName())
                .throwIfInvalid("Pleace specify valid firstName!");

        if (person.isAdmin())
            ValidatorUtil.notNull
                    .and(ValidatorUtil.notEmpty)
                    .and(ValidatorUtil.equalCheck("Petar"))
                    .validate(person.getFirstName())
                    .throwIfInvalid("Admin firstName is not valid.");
    }
}
