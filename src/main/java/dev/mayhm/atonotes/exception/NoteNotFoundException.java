package dev.mayhm.atonotes.exception;

public class NoteNotFoundException extends RuntimeException{

    public NoteNotFoundException() {
        super("Note not found.");
    }
}
