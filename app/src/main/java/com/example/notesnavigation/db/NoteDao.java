package com.example.notesnavigation.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    // the query method
    @Query("DELETE FROM Note")
    void deleteAll();

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNote();

    @Query("SELECT * FROM Note WHERE isFavorite == true")
    LiveData<List<Note>> getAllFavouriteNotes();
}
