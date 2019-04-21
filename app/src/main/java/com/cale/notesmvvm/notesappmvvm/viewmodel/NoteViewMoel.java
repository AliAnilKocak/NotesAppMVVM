package com.cale.notesmvvm.notesappmvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.cale.notesmvvm.notesappmvvm.service.model.Note;
import com.cale.notesmvvm.notesappmvvm.service.repository.NoteRepository;

import java.util.List;

public class NoteViewMoel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewMoel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();

    }
    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.udpate(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotesa();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


}
