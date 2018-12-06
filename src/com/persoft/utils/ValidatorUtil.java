package com.persoft.utils;

import com.persoft.validation.GenericValidation;
import com.persoft.validation.Validation;

import java.util.Objects;

public class ValidatorUtil {

    private ValidatorUtil() { }

    public static Validation<Object> notNull() {
        return GenericValidation.from(Objects::nonNull);
    }

    /**
     * Note: Only valid on string properties
     */
    public static Validation<Object> notEmpty(){
        return GenericValidation.from(s -> !((String) s).isEmpty());
    }

    public static Validation<Object> equalCheck(Object value) {
        return GenericValidation.from(v -> v.equals(value));
    }
    public static Validation<Object> notEqual(Object value) {
        return GenericValidation.from(v -> !v.equals(value));
    }

    /**
     * Note: Only valid on string properties
     */
    public static Validation<Object> maxLength(int limit) {
        return GenericValidation.from(s -> ((String) s).length() <= limit);
    }

    /**
     * Note: Only valid on string properties
     */
    public static Validation<Object> minLength(int size) {
        return GenericValidation.from(s -> ((String) s).length() >= size);
    }

    /**
     * Note: Only valid on string properties
     */
    public static Validation<Object> length(int moreThan, int lessThan) {
        return minLength(moreThan).and(maxLength(lessThan));
    }

    /**
     * Note: Only valid for number properties
     */
    public static Validation<Object> lessThan(Number lessThan) {
        return GenericValidation.from(i -> {
            if(i instanceof Double) {
                return (Double) i < lessThan.doubleValue();
            } else if(i instanceof Float) {
                return (Float) i < lessThan.floatValue();
            } else if(i instanceof Long) {
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
            if(i instanceof Double) {
                return (Double) i <= lessThan.doubleValue();
            } else if(i instanceof Float) {
                return (Float) i <= lessThan.floatValue();
            } else if(i instanceof Long) {
                return (Long) i <= lessThan.longValue();
            } else {
                return (Integer) i <= lessThan.intValue();
            }
        });
    }

}