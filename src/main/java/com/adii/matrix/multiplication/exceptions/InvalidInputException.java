package com.adii.matrix.multiplication.exceptions;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = -8895257681520378752L;

    public InvalidInputException() {
        super("Invalid source has been provided. Please check your input!");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
