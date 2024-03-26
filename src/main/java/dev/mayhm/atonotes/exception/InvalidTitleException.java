package dev.mayhm.atonotes.exception;

public class InvalidTitleException extends RuntimeException{
    public InvalidTitleException() {
        super("No title provided");
    }
}
