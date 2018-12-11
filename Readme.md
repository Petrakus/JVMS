# Java Validation Made Simple
## Requirements
In order to use JVMS(Java Validation Made Simple), your project must use Java 8 +.


## Creating your first validator
In order to use the JVMS validation you just need to import it:
````java
import static com.persoft.validation.JVMS.*;
````
Then imagine that you need to validate a field to not be null or empty:
````java
ValidationResult validationResult = notBlank().validate(firstName);
if(!validationResult.isValid()) {
    System.out.println(validationResult.getMessage());
}
````
In this example we use the built in validation ``notBlank`` , then we tell JVMS which field we want to validate with the ``validate`` method.

The ``forParam`` method returns a ValidationResult object that contains:
* ``isValid()`` - a boolean that says whether the validation succeeded.
* ``getMessage()`` - a String which contains the error message.

## Chaining validators
You can chain multiple validators together for the same property:
````java
ValidationResult validationResult = notNull()
        .and(notEmpty())
        .and(notEqual("foo"))
        .forParam(firstName);
````

## Throwing Exceptions

Instead of returning a ``ValidationResult``, you can tell JVMS to throw an exception if validation fails by using the ``throwIfInvalid`` method:
````java
notBlank().validate(firstName).throwIfInvalid();
````
You just need to catch ``ValidationException``

``throwIfInvalid()`` method will throw an exception with default error message. You can customize that error message by passing the parameter name or by using a custom one:
````java
notBlank().validate(firstName).throwIfInvalid("firstName");
notBlank().validate(firstName).throwIfInvalidCustomMessage("Firstname is required!");
````

## Customizing the error message

``getMessage`` will return a default error message if validation fails. You can customize that error message by passing the parameter name or by using a custom one:
````java
notBlank().validate(firstName).withMessage("firstName");
notBlank().validate(firstName).withCustomMessage("Firstname is required!");
````