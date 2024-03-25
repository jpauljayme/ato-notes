package dev.mayhm.atonotes.service;

import dev.mayhm.atonotes.error.ErrorDetails;
import dev.mayhm.atonotes.exception.InvalidBodyException;
import dev.mayhm.atonotes.exception.InvalidNoteException;
import dev.mayhm.atonotes.exception.InvalidTitleException;
import dev.mayhm.atonotes.exception.NoteNotFoundException;
import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes(){
        return noteRepository.getAllNotes();
    }

    public void createNote(Note note) {
        List<Throwable> exceptions = new ArrayList<>();

        if(note.getTitle().isBlank() ){
            exceptions.add(new InvalidTitleException());
        }

        if(note.getBody().isBlank()){
            exceptions.add(new InvalidBodyException());
        }

        if (!exceptions.isEmpty()) {
            throw new InvalidNoteException(exceptions);
        }

        noteRepository.createNote(note);
    }

    public ErrorDetails updateNote(int id, Note note) {

        ErrorDetails errorDetails = new ErrorDetails();

        if(noteRepository.checkIfIdExists(id)){
            noteRepository.updateNote(note);
        }else{
            throw new NoteNotFoundException();
        }
        return errorDetails;
    }

    public Note getNoteById(int id) {
        Optional<Note> noteById = noteRepository.getNoteById(id);

        if(noteById.isPresent()){
            return noteById.get();
        }else{
            throw new NoteNotFoundException();
        }
    }
}
