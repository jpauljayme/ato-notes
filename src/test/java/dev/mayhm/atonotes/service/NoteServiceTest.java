package dev.mayhm.atonotes.service;

import dev.mayhm.atonotes.model.Note;
import dev.mayhm.atonotes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.internal.matchers.Not;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NoteServiceTest {


    @Mock
    NoteRepository mockedNoteRepository;

    @InjectMocks
    NoteService noteService;

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
    void updateNote() {

        int givenId = 2;

        Note givenNote = new Note(2,
                "Second Note Updated",
                "There are two archetypes existing within me.");

        given(mockedNoteRepository.checkIfIdExists(givenId))
                .willReturn(true);

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);

        noteService.updateNote(givenId, givenNote);

        BDDMockito.doNothing().when(mockedNoteRepository).updateNote(givenNote);
        BDDMockito.then(mockedNoteRepository).should().updateNote(noteCaptor.capture());

        // Get the captured argument
        Note capturedNote = noteCaptor.getValue();

        // Assert that the captured ID is correct
        assertEquals(givenNote, capturedNote);

    }

    @Test
    void givenUserWantsToDelete_WhenGivenNoteId_ThenDeleteNote() {

        List<Note> initialNotes = new ArrayList<>();
        initialNotes.add(new Note(1,
                "First Note",
                "This is the start of something!"));
        initialNotes.add(new Note(2,
                "Second Note",
                "This is rising action of something! Huh..."));

        int givenId = 1;

        BDDMockito.when(mockedNoteRepository.getAllNotes()).thenReturn(initialNotes);

        given(mockedNoteRepository.checkIfIdExists(givenId))
                .willReturn(true);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

        noteService.deleteNote(givenId);

        BDDMockito.doNothing().when(mockedNoteRepository).deleteNote(givenId);
        BDDMockito.then(mockedNoteRepository).should().deleteNote(argumentCaptor.capture());

        // Get the captured argument
        int capturedId = argumentCaptor.getValue();

        // Assert that the captured ID is correct
        assertEquals(givenId, capturedId);
    }

    @Test
    void 

}
