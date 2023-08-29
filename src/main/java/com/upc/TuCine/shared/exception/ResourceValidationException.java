package com.upc.TuCine.shared.exception;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class ResourceValidationException extends RuntimeException{
    public ResourceValidationException() {
        super();
    }
    public ResourceValidationException(String message) {
        super(message);
    }
    public ResourceValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public <T> ResourceValidationException(String resourceName, Set<ConstraintViolation<T>> violations) {
        super(String.format("Not all constraints satisfied for %s: %s", resourceName,
                violations.stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(". "))));
    }
}
