package com.persoft.validation;

import java.util.function.Predicate;

public class GenericValidation<K> implements Validation<K> {
    private Predicate<K> predicate;
    private String onErrorMessage;

    static <K> GenericValidation<K> from(Predicate<K> predicate, String onErrorMessage) {
        return new GenericValidation<>(predicate, onErrorMessage);
    }

    private GenericValidation(Predicate<K> predicate, String onErrorMessage) {
        this.predicate = predicate;
        this.onErrorMessage = onErrorMessage;
    }

    @Override
    public ValidationResult validate(K param) {
        return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage);
    }
}
