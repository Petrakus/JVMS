package com.persoft.validation;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private ValidatorUtil() {
    }

    public static Validation<Object> notNull() {
        return GenericValidation.from(Objects::nonNull);
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> notEmpty() {
        return GenericValidation.from(s -> !((String) s).isEmpty());
    }

    public static Validation<Object> isEqual(Object value) {
        return GenericValidation.from(v -> v.equals(value));
    }

    public static Validation<Object> notEqual(Object value) {
        return GenericValidation.from(v -> !v.equals(value));
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> maxLength(int limit) {
        return GenericValidation.from(s -> ((String) s).length() <= limit);
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> minLength(int size) {
        return GenericValidation.from(s -> ((String) s).length() >= size);
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
        });
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
        });
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
        });
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
        });
    }

    /**
     * Custom predicate validation
     */
    public static <K> Validation<K> must(Predicate<K> predicate) {
        return GenericValidation.from(predicate);
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> matches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return GenericValidation.from(s -> pattern.matcher((String)s).matches());
    }

    /**
     * Note: Only valid for string properties
     */
    public static Validation<Object> isEmail() {
        String emailRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        return matches(emailRegex);
    }

}