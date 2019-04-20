package com.cale.notesmvvm.notesappmvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;


    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    public void insert(Note note) {
        new InsertNotesAsyncTask(noteDao).execute(note);
    }

    public void udpate(Note note) {
        new UpdateNotesAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new deleteNotesAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotesa() {
        new deleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class InsertNotesAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private InsertNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNotesAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private UpdateNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    public static class deleteNotesAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private deleteNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    public static class deleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private deleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}

