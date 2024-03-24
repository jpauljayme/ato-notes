package dev.mayhm.atonotes.controller;

import dev.mayhm.atonotes.dto.ApiResponse;
import dev.mayhm.atonotes.error.ErrorDetails;
import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.service.NoteService;
import org.springframework.http.HttpStatus;
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

    @PostMapping()
    public ApiResponse createNote(@RequestBody Note note){
        ErrorDetails errors = noteService.createNote(note);
      
        return errors.isNoError() ? new ApiResponse(HttpStatus.CREATED, note)
                : new ApiResponse(HttpStatus.BAD_REQUEST, errors);
    }

}
