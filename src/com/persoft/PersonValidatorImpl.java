package com.persoft;

public class PersonValidatorImpl implements PersonValidator {
    @Override
    public void validate(Person person) {

        ValidatorUtil.notNullString
                .and(ValidatorUtil.notEmptyString)
                .test(person.getFirstName())
                .throwIfInvalid("Pleace specify valid firstName!");

        ValidatorUtil.notNullInteger
                .and(ValidatorUtil.integerMoreThan(18))
                .test(person.getAge())
                .throwIfInvalid("Pleace specify valit age. More than 18 years.");

        if (person.isAdmin())
            ValidatorUtil.notNullString
                    .and(ValidatorUtil.notEmptyString)
                    .and(ValidatorUtil.equals("Petar"))
                    .test(person.getFirstName())
                    .throwIfInvalid("Admin firstName is not valid.");
    }
}
