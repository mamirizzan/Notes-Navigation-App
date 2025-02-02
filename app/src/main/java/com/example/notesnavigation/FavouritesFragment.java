package com.example.notesnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notesnavigation.db.Note;
import com.example.notesnavigation.db.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FavouritesFragment extends Fragment {

    private NoteViewModel noteViewModel;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup ViewModel
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.RecycleViewFavNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Setup Adapter
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        // Observe changes in notes
        LiveData<List<Note>> allFavNotes = noteViewModel.getAllFavouriteNotes();

        // Shortcut
        allFavNotes.observe(getViewLifecycleOwner(), adapter::setNotes);

        // Long way
//        allFavNotes.observe(getViewLifecycleOwner(), notes -> {
//            adapter.setNotes(notes);
//        });


//        // Setup FloatingActionButton for adding a note
//        FloatingActionButton fab = view.findViewById(R.id.FABAddNote);
//        fab.setOnClickListener(v -> {
//            // Navigate to AddNoteFragment
//            Navigation.findNavController(view).navigate(R.id.addNoteFragment);
//        });
    }
}