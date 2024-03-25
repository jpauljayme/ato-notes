package dev.mayhm.atonotes.service;

import dev.mayhm.atonotes.error.ErrorDetails;
import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NoteServiceTest {


    @Mock
    static NoteRepository mockedNoteRepository;

    @InjectMocks
    static NoteService noteService;

    @Test
    void getAllNotesHappyFlow() {

        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(new Note(1,
                "First Note",
                "This is the start of something!"));
        expectedNotes.add(new Note(2,
                "Second Note",
                "This is rising action of something! Huh..."));

        given(mockedNoteRepository.getAllNotes()).willReturn(expectedNotes);

        List<Note> actualNotes = noteService.getAllNotes();

        assertEquals(expectedNotes, actualNotes);
        verify(mockedNoteRepository).getAllNotes();
    }

    @Test
    void getNoteByIdHappyFlow() {


        Note expectedNote = new Note(1,
                "First Note",
                "This is the start of something!");

        given(mockedNoteRepository
                .getNoteById(expectedNote.getId()))
                .willReturn(Optional.of(expectedNote));

        Note actualNote = noteService.getNoteById(1);

        assertEquals(expectedNote,actualNote);

        then(mockedNoteRepository)
                .should(times(1))
                .getNoteById(1);
    }

    @Test
    void whenGetNoteByIdNotExists_thenReturnNull() {

        int invalidId = 999;
        Optional<Note> expectedNote = Optional.<Note>of(null);

        given(mockedNoteRepository
                .getNoteById(invalidId))
                .willReturn(expectedNote);

        Note actualNote = noteService.getNoteById(1);

        assertEquals(expectedNote,actualNote);

        then(mockedNoteRepository)
                .should(times(1))
                .getNoteById(1);
    }

    @Test
    void createNote() {
    }



    @Test
    void updateNote() {

        Note expectedNote = new Note(2,
                "Second Note Updated",
                "There are two archetypes existing within me.");

        then(mockedNoteRepository)
                .should(times(1))
                .updateNote(expectedNote);

        verify(mockedNoteRepository).updateNote(expectedNote);
    }
}
