package org.example.exceptions;

public class ScoreCannotBeNegativeException extends Exception{
    public ScoreCannotBeNegativeException(String message) {
        super(message);
    }
}
