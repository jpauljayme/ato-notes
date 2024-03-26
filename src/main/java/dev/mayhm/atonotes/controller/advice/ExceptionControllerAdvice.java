package dev.mayhm.atonotes.controller.advice;

import dev.mayhm.atonotes.error.ErrorDetails;
import dev.mayhm.atonotes.error.NoteError;
import dev.mayhm.atonotes.exception.InvalidNoteException;
import dev.mayhm.atonotes.exception.NoteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(NoteNotFoundException.class)
  public ResponseEntity<String> exceptionNoteNotFoundHandler(NoteNotFoundException exception) {
    ErrorDetails errorDetails = new ErrorDetails();

    NoteError noteError = new NoteError("Note not found.");

    errorDetails.addError(noteError);

    return ResponseEntity
        .badRequest()
        .body(exception.getMessage());
  }

  @ExceptionHandler(InvalidNoteException.class)
  public ResponseEntity<List<String>> exceptionInvalidNoteCreationHandler(InvalidNoteException exception) {
    StringBuilder message = new StringBuilder();
    List<String> messages = new ArrayList<>();
    for (Throwable ex : exception.getExceptions()) {
      messages.add(ex.getMessage());
    }


    return ResponseEntity
            .badRequest()
            .body(messages);
  }
}
