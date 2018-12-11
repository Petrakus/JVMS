package com.persoft;

import com.persoft.validation.ValidationResult;

import javax.xml.bind.ValidationException;

import static com.persoft.validation.JVMS.isEqual;
import static com.persoft.validation.JVMS.notBlank;

public class Main {

    public static void main(String[] args) {
        String firstName = "John";

        // Simple validation
        ValidationResult validationResult = notBlank().and(isEqual("foo")).validate(firstName);
        if(!validationResult.isValid()) {
            System.out.println(validationResult.getMessage());
        }

        // Validation by using the withMessage() method with param name.
        String errMsg = notBlank().validate(firstName).withMessage("firstName").getMessage();
        if(errMsg != null) {
            System.out.println(errMsg);
        }

        // Validation by using the withCustomMessage() method.
        String errMsg2 = notBlank().validate(firstName).withCustomMessage("Firstname is required!").getMessage();
        if(errMsg != null) {
            System.out.println(errMsg2);
        }

        // Throwing and error
        try {
            notBlank().validate(firstName).throwIfInvalid();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // Throwing and error with param name.
        try {
            notBlank().validate(firstName).throwIfInvalid("firstName");
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // Throwing and error with custom message
        try {
            notBlank().validate(firstName).throwIfInvalidCustomMessage("Firstname is required!");
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }
}
