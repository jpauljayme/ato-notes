package dev.mayhm.atonotes.repository;

import dev.mayhm.atonotes.model.Note;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class NoteRepository {

    private List<Note> notes;

    public NoteRepository() {
        this.notes = List.of(new Note(1,
                        "First Note",
                        "This is the start of something!"),
                new Note(2,
                        "Second Note",
                        "This is rising action of something! Huh..."));
    }

    public List<Note> getAllNotes() {
        return Collections.unmodifiableList(notes);
    }
}
