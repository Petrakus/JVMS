# Java Validation Made Simple
## Requirements
In order to use JVMS(Java Validation Made Simple), your project must be Java 8+.


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

The ``validate`` method returns a ValidationResult object that contains:
* ``isValid()`` - a boolean that says whether the validation succeeded.
* ``getMessage()`` - a String which contains the error message.

## Chaining validators
You can chain multiple validators together for the same property:
````java
ValidationResult validationResult = notNull()
        .and(notEmpty())
        .and(notEqual("foo"))
        .validate(firstName);
````

## Throwing Exceptions

Instead of returning a ``ValidationResult``, you can tell JVMS to throw an exception if validation fails by using the ``throwIfInvalid`` method:
````java
notBlank().validate(firstName).throwIfInvalid();
````
You just need to catch ``ValidationException``

``throwIfInvalid()`` method will throw an exception with default error message. You can customize that error message by passing the parameter name or by using a custom one:
````java
// Example error: Must not be null.
notBlank().validate(firstName).throwIfInvalid();

// Example error: firstName : Must not be null.
notBlank().validate(firstName).throwIfInvalid("firstName");

// Example error: Firstname is required!
notBlank().validate(firstName).throwIfInvalidCustomMessage("Firstname is required!");
````

## Customizing the error message

``getMessage`` will return a default error message if validation fails. You can customize that error message by passing the parameter name or by using a custom one:
````java
// Example error: firstName : Must not be null.
notBlank().validate(firstName).withMessage("firstName");

// Example error: Firstname is required!
notBlank().validate(firstName).withCustomMessage("Firstname is required!");
````


# Built-in Validators
JVSM ships with several built-in validators.

### notNull Validator
Description: Ensures that the specified property is not null.

Example:
````java
notNull().validate(firstName);
````
Example error: must not be null.

### notEmpty Validator
Description: Ensures that the specified property is not an empty string or whitespace.

Example:
````java
notEmpty().validate(firstName);
````
Example error: should not be empty.

### notBlank Validator
Description: Ensures that the specified property is not null or empty.

Example:
````java
notBlank().validate(firstName);
````
Example error: must not be null / should not be empty.

### isEqual Validator
Description: Ensures that the value of the specified property is equal to a particular value.

Example:
````java
isEqual("Foo").validate(firstName);
````
Example error: should be equal to 'Foo'.

### notEqual Validator
Description: Ensures that the value of the specified property is not equal to a particular value.

Example:
````java
notEqual("Foo").validate(firstName);
````
Example error: should not be equal to 'Foo' .

### length Validator
Description: Ensures that the length of a particular string property is within the specified range. However, it doesn’t ensure that the string property isn’t null.

Example:
````java
length(1, 250).validate(firstName);
````
Example error: must be between 1 and 250 characters.
Note: Only valid on string properties. 

### maxLength Validator
Description: Ensures that the length of a particular string property is no longer than the specified value.

Example:
````java
maxLength(10).validate(firstName);
````
Example error: must be 10 characters or fewer.
Note: Only valid on string properties. 

### minLength Validator
Description: Ensures that the length of a particular string property is longer than the specified value.

Example:
````java
minLength(10).validate(firstName);
````
Example error: must be at least 10 characters.
Note: Only valid on string properties. 

### lessThan Validator
Description: Ensures that the value of the specified property is less than a particular value.

Example:
````java
lessThan(10).validate(age);
````
Example error: must be less than 10.
Note: Only valid on number properties. 

### lessThanOrEqual Validator
Description: Ensures that the value of the specified property is less than or equal to a particular value.

Example:
````java
lessThanOrEqual(10).validate(age);
````
Example error: must be less than or equal to 10.
Note: Only valid on number properties. 

### greaterThan Validator
Description: Ensures that the value of the specified property is greater than a particular value.

Example:
````java
greaterThan(10).validate(age);
````
Example error: must be greater than 10.
Note: Only valid on number properties. 

### greaterThanOrEqual Validator
Description: Ensures that the value of the specified property is greater than or equal to a particular value.

Example:
````java
greaterThanOrEqual(10).validate(age);
````
Example error: must be greater than or equal to 10.
Note: Only valid on number properties. 

### matches Validator
Description: Ensures that the value of the specified property matches the given regular expression. 

Example:
````java
matches("some regex").validate(email);
````
Example error: is not in the correct format.
Note: Only valid on string properties. 

### isEmail Validator
Description: Ensures that the value of the specified property is a valid email address format. 

Example:
````java
isEmail().validate(email);
````
Example error: is not a valid email address.
Note: Only valid on string properties. 

### must Validator
Description: Accepting a user defined predicate. 

Example:
````java
must(s -> s.equals("test")).validate(firstName);
````
Example error: not matching the predicate defined.

### isCreditCardNumber Validator
Description: Validating a credit card number - visa, mastercard, discover, american express, diners, jcb. 

Example:
````java
isCreditCardNumber().validate(cardNumber);
````
Example error: is not a valid credit card number.

## Authors

* **Petar Petrov** - *Initial work* - [Petrakus](https://github.com/Petrakus)

See also the list of [contributors](https://github.com/Petrakus/JVMS/graphs/contributors) who participated in this project.
