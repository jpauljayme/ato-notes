package dev.mayhm.atonotes.service;

import dev.mayhm.atonotes.dto.ApiResponse;
import dev.mayhm.atonotes.error.ErrorDetails;
import dev.mayhm.atonotes.error.NoteError;
import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes(){
        return noteRepository.getAllNotes();
    }

    public ErrorDetails createNote(Note note) {

        ErrorDetails errorDetails = new ErrorDetails();

        if(note.getTitle().isBlank() ){
            errorDetails.addError(new NoteError("INVALID_TITLE",
                    "Title cannot be blank."));
        }

        if(note.getBody().isBlank()){
            errorDetails.addError(new NoteError("INVALID_BODY",
                    "Body cannot be blank."));
        }

        if(errorDetails.isNoError()){
            noteRepository.createNote(note);
        }

        return errorDetails;
    }

    public ErrorDetails updateNote(int id, Note note) {

        ErrorDetails errorDetails = new ErrorDetails();

        if(noteRepository.checkIfIdExists(id)){
            noteRepository.updateNote(note);
        }else{
            errorDetails.addError(new NoteError("INVALID_ID",
                    "Note not found"));
        }
        return errorDetails;
    }
}
