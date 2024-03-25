package dev.mayhm.atonotes.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorDetails {

    List<NoteError> errorList = new ArrayList<>();

    public void addError(NoteError error) {
        errorList.add(error);
    }

    public boolean isNoError(){
        return errorList.isEmpty();
    }

    public List<NoteError> getErrorList() {
        return errorList;
    }
}

