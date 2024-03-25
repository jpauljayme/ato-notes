package dev.mayhm.atonotes.controller;

import dev.mayhm.atonotes.dto.ApiResponse;
import dev.mayhm.atonotes.error.ErrorDetails;
import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/notes")
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
    public ResponseEntity<Note> getNoteById(@PathVariable int id){
        Note noteById = noteService.getNoteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                    .body(noteById);

    }

    @PostMapping()
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        noteService.createNote(note);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(note);
        
    }

    @PutMapping("/{id}")
    public ApiResponse updateNote(@PathVariable int id,
                                  @RequestBody Note note){
        ErrorDetails errors = noteService.updateNote(id,note);

        return errors.isNoError() ? new ApiResponse(HttpStatus.OK, note, null)
                : new ApiResponse(HttpStatus.BAD_REQUEST, note, errors);

    }
}
