package com.example.notesnavigation.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes, allFavNotes;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNote();
        allFavNotes = noteDao.getAllFavouriteNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }

    public LiveData<List<Note>> getAllFavouriteNotes(){
        return allFavNotes;
    }


}
