package dev.mayhm.atonotes.controller;

import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.service.NoteService;
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
    public String createNote(@RequestBody Note note){
        return note.toString();
    }

}
