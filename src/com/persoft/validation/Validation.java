package com.persoft.validation;

@FunctionalInterface
public interface Validation<K> {
    GenericValidationResult validate(K param);

    default Validation<K> and(Validation<K> other) {
        return param -> {
            GenericValidationResult result = this.validate(param);
            return !result.isValid() ? result : other.validate(param);
        };
    }

    default Validation<K> or(Validation<K> other) {
        return param -> {
            GenericValidationResult result = this.validate(param);
            return result.isValid() ? result : other.validate(param);
        };
    }
}