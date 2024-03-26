package dev.mayhm.atonotes.controller.advice;

import dev.mayhm.atonotes.exception.InvalidNoteException;
import dev.mayhm.atonotes.exception.NoteNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(NoteNotFoundException.class)
  public ResponseEntity<String> exceptionNoteNotFoundHandler(NoteNotFoundException exception) {

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

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    String parameterName = ex.getName();
    String parameterType = ex.getRequiredType().getSimpleName();
    String errorMessage = String.format("Invalid value for parameter '%s'. Expected type: %s", parameterName, parameterType);
    return ResponseEntity.badRequest().body(errorMessage);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {

    StringBuilder errorMessage = new StringBuilder();
    ex.getConstraintViolations().forEach(violation ->
            errorMessage.append(violation.getPropertyPath() + " " + violation.getMessage()).append("; "));
    return ResponseEntity.badRequest().body(errorMessage.toString());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    String errorMessage = "Invalid request format: " + ex.getMessage();
    return ResponseEntity.badRequest().body(errorMessage);
  }
}
