package com.persoft;

public class Main {

    public static void main(String[] args) {
        // write your code here
        PersonValidator personValidator = new PersonValidatorImpl();
        try {
            personValidator.validate(new Person("Petar", "Petrov", "p.petrov@ibs.bg", 19, 'm', true));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
