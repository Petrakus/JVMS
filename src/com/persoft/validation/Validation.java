package com.persoft.validation;

@FunctionalInterface
public interface Validation<K> {
    GenericValidationResult forParam(K param);

    default Validation<K> and(Validation<K> other) {
        return param -> {
            GenericValidationResult result = this.forParam(param);
            return !result.isValid() ? result : other.forParam(param);
        };
    }

    default Validation<K> or(Validation<K> other) {
        return param -> {
            GenericValidationResult result = this.forParam(param);
            return result.isValid() ? result : other.forParam(param);
        };
    }
}