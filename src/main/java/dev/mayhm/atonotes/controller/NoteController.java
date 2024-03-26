package dev.mayhm.atonotes.controller;

import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/notes")
@Validated
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public List<Note> getNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable @Min(1) int id){
        Note noteById = noteService.getNoteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                    .body(noteById);

    }

    @PostMapping()
    public ResponseEntity<Note> createNote(@RequestBody @Valid Note note){
        Note createdNote = noteService.createNote(note);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdNote);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id,
                                           @RequestBody
                                           @Valid
                                           Note note){
        noteService.updateNote(id,note);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(note);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable @Valid int id){
        noteService.deleteNote(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Deleted note with id " + id);
    }
}
