package com.adii.matrix.multiplication.exceptions;

public class MatrixIOException extends RuntimeException {

    private static final long serialVersionUID = -8895257681520378752L;

    public MatrixIOException() {
        super("There has a been a problem with your file. Try with another.");
    }

    public MatrixIOException(String message) {
        super(message);
    }
}
