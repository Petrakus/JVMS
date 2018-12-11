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
        return GenericValidation.from(v -> v.equals(value), format("must be equal to %s", value));
    }

    public static Validation<Object> notEqual(Object value) {
        return GenericValidation.from(v -> !v.equals(value), format("must not be equal to %s", value));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> maxLength(int limit) {
        return GenericValidation.from(s -> ((String) s).length() <= limit, format("must have less than %s or equal %s chars.", limit, limit));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> minLength(int size) {
        return GenericValidation.from(s -> ((String) s).length() >= size, format("must have more than %s or equal %s chars.", size, size));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> length(int moreThan, int lessThan) {
        return minLength(moreThan).and(maxLength(lessThan));
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
        }, format("must be less than %s", lessThan));
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
        }, format("must be less than %s or equal to %s", lessThan, lessThan));
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
        }, format("must be greater than %s", lessThan));
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
        }, format("must be greater than %s or equal to %s", lessThan, lessThan));
    }

    /**
     * Custom predicate validation
     */
    public static <K> Validation<K> must(Predicate<K> predicate) {
        return GenericValidation.from(predicate, "not matching the predicate defined");
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> matches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return GenericValidation.from(s -> pattern.matcher((String)s).matches(), format("must match the pattern %s", regex));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> isEmail() {
        String emailRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        return matches(emailRegex);
    }
}
