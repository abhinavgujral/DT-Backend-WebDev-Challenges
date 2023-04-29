package com.DTedutech.task1.Exception;

public class InsufficientResourceException extends RuntimeException {
    public InsufficientResourceException(String addRelevantParametersInUri) {
        super(addRelevantParametersInUri);
    }
}
