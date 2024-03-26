package dev.mayhm.atonotes.exception;

public class InvalidBodyException extends RuntimeException{

    public InvalidBodyException() {
        super("No body provided");
    }
}
