package dev.mayhm.atonotes.repository;

import dev.mayhm.atonotes.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Note createNote(Note note) {

        Note noteWithId = new Note(notes.size() + 1,
                note.getTitle(),
                note.getBody());
        notes.add(noteWithId);
        return noteWithId;
    }

    public boolean checkIfIdExists(int id) {
        for(Note note : notes){
            if(note.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void updateNote(Note updatedNote) {
        notes.stream().filter(note -> note.getId() == updatedNote.getId())
                .findFirst()
                .ifPresent(note -> {
                    note.setBody(updatedNote.getBody());
                    note.setTitle(updatedNote.getTitle());
                });
    }

    public Optional<Note> getNoteById(int id) {
        return notes.stream().filter(note -> note.getId() == id)
                .findFirst();
    }
}
