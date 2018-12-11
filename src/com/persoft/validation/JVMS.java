package com.persoft.validation;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class JVMS {
    private JVMS(){}

    public static Validation<Object> notNull() {
        return GenericValidation.from(Objects::nonNull, "must not be null.");
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> notEmpty() {
        return GenericValidation.from(s -> !((String) s).trim().isEmpty(), "must not be empty.");
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> notBlank() {
        return notNull().and(notEmpty());
    }

    public static Validation<Object> isEqual(Object value) {
        return GenericValidation.from(v -> v.equals(value), format("must be equal to %s.", value));
    }

    public static Validation<Object> notEqual(Object value) {
        return GenericValidation.from(v -> !v.equals(value), format("must not be equal to %s.", value));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> maxLength(int limit) {
        return GenericValidation.from(s -> ((String) s).length() <= limit, format("must be %s characters or fewer.", limit));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> minLength(int size) {
        return GenericValidation.from(s -> ((String) s).length() >= size, format("must be at least %s characters.", size));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> length(int moreThan, int lessThan) {
        return GenericValidation.from(s -> ((String) s).length() >= moreThan && ((String) s).length() <= lessThan, format("must be between %s and %s characters.", moreThan, lessThan));
    }

    /**
     * Note: Only valid for number properties
     */
    public static Validation<Object> lessThan(Number lessThan) {
        return GenericValidation.from(i -> {
            if (i instanceof Double) {
                return (Double) i < lessThan.doubleValue();
            } else if (i instanceof Float) {
                return (Float) i < lessThan.floatValue();
            } else if (i instanceof Long) {
                return (Long) i < lessThan.longValue();
            } else {
                return (Integer) i < lessThan.intValue();
            }
        }, format("must be less than %s.", lessThan));
    }

    /**
     * Note: Only valid for number properties
     */
    public static Validation<Object> lessThanOrEqual(Number lessThan) {
        return GenericValidation.from(i -> {
            if (i instanceof Double) {
                return (Double) i <= lessThan.doubleValue();
            } else if (i instanceof Float) {
                return (Float) i <= lessThan.floatValue();
            } else if (i instanceof Long) {
                return (Long) i <= lessThan.longValue();
            } else {
                return (Integer) i <= lessThan.intValue();
            }
        }, format("must be less than or equal to %s.", lessThan));
    }

    /**
     * Note: Only valid for number properties
     */
    public static Validation<Object> greaterThan(Number lessThan) {
        return GenericValidation.from(i -> {
            if (i instanceof Double) {
                return (Double) i > lessThan.doubleValue();
            } else if (i instanceof Float) {
                return (Float) i > lessThan.floatValue();
            } else if (i instanceof Long) {
                return (Long) i > lessThan.longValue();
            } else {
                return (Integer) i > lessThan.intValue();
            }
        }, format("must be greater than %s.", lessThan));
    }

    /**
     * Note: Only valid for number properties
     */
    public static Validation<Object> greaterThanOrEqual(Number lessThan) {
        return GenericValidation.from(i -> {
            if (i instanceof Double) {
                return (Double) i >= lessThan.doubleValue();
            } else if (i instanceof Float) {
                return (Float) i >= lessThan.floatValue();
            } else if (i instanceof Long) {
                return (Long) i >= lessThan.longValue();
            } else {
                return (Integer) i >= lessThan.intValue();
            }
        }, format("must be greater than or equal to %s.", lessThan));
    }

    /**
     * Custom predicate validation
     */
    public static <K> Validation<K> must(Predicate<K> predicate) {
        return GenericValidation.from(predicate, "not matching the predicate defined.");
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> matches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return GenericValidation.from(s -> pattern.matcher((String)s).matches(), "is not in the correct format.");
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> isEmail() {
        String emailRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(emailRegex);
        return GenericValidation.from(s -> pattern.matcher((String)s).matches(), "is not a valid email address.");
    }
}
