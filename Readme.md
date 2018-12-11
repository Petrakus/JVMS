# Java Validation Made Simple
## Requirements
In order to use JVMS(Java Validation Made Simple), your project must use Java 8 +.

## Creating your first validator

For example, imagine that you have a Person class:
```java
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private char gender;
}
```

You would define a set of validation rules for this class by creating a PersonValidator class.
In order to use the built in validations your class must implement the DefaultValidator interface:

````java
public class PersonValidator implements DefaultValidator {

    public boolean validate(Person person) {
        GenericValidationResult validationResult = isEmail().forParam(person.getEmail());
        return validationResult.isValid();
    }
}
````

The ``forParam`` method returns a GenericValidationResult object that contains:
* ``isValid()`` - a boolean that says whether the validation succeeded.
Example:
````java
GenericValidationResult validationResult = isEmail().forParam(person.getEmail());
````
## Chaining validators
You can chain multiple validators together for the same property:
````java
GenericValidationResult validationResult = notNull()
        .and(notEmpty())
        .and(notEqual("foo"))
        .forParam(person.getFirstName());
````
This would ensure that the firstName is not null, not empty and is not equal to the string ‘foo’.

Instead of using notNull() and notEmpty() methods, you can use notBlank() which combines them:
````java
GenericValidationResult validationResult = notBlank()
        .and(notEqual("foo"))
        .forParam(person.getFirstName());
````

## Throwing Exceptions

Instead of returning a ``GenericValidationResult``, you can alternatively tell JVMS to throw an exception if validation fails by using the ``throwIfInvalid`` method:
````java
public class PersonValidatorWithException implements DefaultValidator {

    public void validate(Person person) throws ValidationException {
        isEmail().forParam(person.getEmail()).throwIfInvalid("Not a valid email");
    }
}
````

## Returning error message

Instead of returning a ``GenericValidationResult``, you can alternatively tell JVMS to return an error message if validation fails by using the ``withMessage`` method, which returns ``Optinal<String>``:
````java
public class PersonValidatorWithException implements DefaultValidator {

    public void validate(Person person) throws ValidationException {
        Optonal<String> error = isEmail().forParam(person.getEmail()).withMessage("Not a valid email");
    }
}
````