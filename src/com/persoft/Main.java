package com.persoft;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Person person = new Person("Test", "Test", "test@gmail.com", 28, 'm', true);

        // Example validator which returns errors list
        PersonValidatorWithErrorsList personValidator = new PersonValidatorWithErrorsList();
        List<Optional<String>> errors = personValidator.validate(person);
        errors.forEach(e-> e.ifPresent(System.out::println));

        // Example validator which throws ValidationException if validation fails
        PersonValidatorWithException personValidatorWithException = new PersonValidatorWithException();
        try {
            personValidatorWithException.validate(person);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
