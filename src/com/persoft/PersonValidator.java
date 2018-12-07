package com.persoft;

import com.persoft.validation.ValidatorUtil;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PersonValidator implements ValidatorUtil {

    public void validate(Person person) throws ValidationException {

        List<Optional<String>> errors = new ArrayList<>();

        errors.add(isEmail().validate(person.getEmail()).withMessage("Not valid email"));

        errors.add(notBlank()
                .and(length(2,5))
                .validate(person.getFirstName())
                .withMessage("Please specify valid firstName!"));

        errors.add(notNull().and(lessThanOrEqual(28)).validate(person.getAge()).withMessage("Age is not valid"));


        errors.add(must(age -> (Integer)age < 20).validate(person.getAge()).withMessage("Age is not valid by the predicate definition"));

        errors.forEach(e-> e.ifPresent(System.out::println));

        if (person.isAdmin())
            notBlank()
                    .and(isEqual("Petar"))
                    .validate(person.getFirstName())
                    .throwIfInvalid("Admin firstName is not valid.");
    }
}
