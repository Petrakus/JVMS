package com.persoft.utils;

import com.persoft.validation.GenericValidation;
import com.persoft.validation.Validation;

import java.util.Objects;

public class ValidatorUtil {

    private ValidatorUtil() { }

    public static final Validation<Object> notNull = GenericValidation.from(Objects::nonNull);
    public static final Validation<Object> notEmpty = GenericValidation.from(s -> !((String) s).isEmpty());

    public static Validation<Object> equalCheck(Object value) {
        return GenericValidation.from(v -> v.equals(value));
    }
    public static Validation<Object> notEqual(Object value) {
        return GenericValidation.from(v -> !v.equals(value));
    }

    public static Validation<Object> maxLength(int limit) {
        return GenericValidation.from(s -> ((String) s).length() <= limit);
    }

    public static Validation<Object> minLength(int size) {
        return GenericValidation.from(s -> ((String) s).length() >= size);
    }

    public static Validation<Object> length(int moreThan, int lessThan) {
        return maxLength(moreThan).and(minLength(lessThan));
    }

}