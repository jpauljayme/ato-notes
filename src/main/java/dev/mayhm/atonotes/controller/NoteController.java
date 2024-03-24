package dev.mayhm.atonotes.controller;

import dev.mayhm.atonotes.model.Note;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/notes")
public class NoteController {

    @GetMapping()
    public List<Note> getNotes(){
        return List.of(new Note(1,
                    "First Note",
                "This is the start of something!"),
                new Note(2,
                        "Second Note",
                        "This is rising action of something! Huh..."));
    }
    
}
