package com.persoft.validation;

@FunctionalInterface
public interface Validation<K> {
    ValidationResult validate(K param);

    default Validation<K> and(Validation<K> other) {
        return param -> {
            ValidationResult result = this.validate(param);
            return !result.isValid() ? result : other.validate(param);
        };
    }

    default Validation<K> or(Validation<K> other) {
        return param -> {
            ValidationResult result = this.validate(param);
            return result.isValid() ? result : other.validate(param);
        };
    }
}