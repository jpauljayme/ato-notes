package dev.mayhm.atonotes.repository;

import dev.mayhm.atonotes.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class NoteRepository {

    private List<Note> notes ;

    public NoteRepository() {
        notes= new ArrayList<>();
        notes.add(new Note(1,
                        "First Note",
                        "This is the start of something!"));
        notes.add(new Note(2,
                        "Second Note",
                        "This is rising action of something! Huh..."));
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }

    public boolean createNote(Note note) {
        return notes.add(note);
    }

}
