package com.persoft;

import javax.xml.bind.ValidationException;

public class Main {

    public static void main(String[] args) {
        PersonValidator personValidator = new PersonValidator();
        try {
            personValidator.validate(new Person("Petar", "Petrov", "p.petrov@ibs.bg", 28, 'm', true));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
