package com.example.notesnavigation.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {
    private static volatile NoteRoomDatabase INSTANCE;
    public abstract NoteDao noteDao();

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NoteRoomDatabase.class, "note_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
