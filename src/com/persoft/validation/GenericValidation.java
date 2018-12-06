package com.persoft.validation;

import java.util.function.Predicate;

public class GenericValidation<K> implements Validation<K> {
    private Predicate<K> predicate;

    public static <K> GenericValidation<K> from(Predicate<K> predicate) {
        return new GenericValidation<>(predicate);
    }

    private GenericValidation(Predicate<K> predicate) {
        this.predicate = predicate;
    }

    @Override
    public GenericValidationResult validate(K param) {
        return predicate.test(param) ? GenericValidationResult.ok() : GenericValidationResult.fail();
    }
}
