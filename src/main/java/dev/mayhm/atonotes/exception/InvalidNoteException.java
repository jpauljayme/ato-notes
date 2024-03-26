package dev.mayhm.atonotes.exception;

import java.util.List;

public class InvalidNoteException extends RuntimeException{

    private final List<Throwable> exceptions;

    public InvalidNoteException(List<Throwable> exceptions) {
        this.exceptions = exceptions;
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }
}
