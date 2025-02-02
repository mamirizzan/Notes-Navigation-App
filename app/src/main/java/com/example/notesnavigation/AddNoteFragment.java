package com.example.notesnavigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.notesnavigation.db.Note;
import com.example.notesnavigation.db.NoteViewModel;

public class AddNoteFragment extends Fragment {

    private EditText ETTitle, ETBody;
    private RadioButton RBFav, RBNotFav;
    private NoteViewModel noteViewModel;

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Initialize Views
        ETTitle = view.findViewById(R.id.ETTitle);
        ETBody = view.findViewById(R.id.ETBody);
        RBFav = view.findViewById(R.id.RBFav);
        RBNotFav = view.findViewById(R.id.RBNotFav);
        Button BtnSave = view.findViewById(R.id.BtnSave);

        BtnSave.setOnClickListener(v -> {
            // Get user input
            String title = ETTitle.getText().toString().trim();
            String body = ETBody.getText().toString().trim();
            boolean isFavourite = RBFav.isChecked();

            // Validate input
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(body)) {
                ETTitle.setError("Title is required!");
                ETBody.setError("Body is required!");
                return;
            }

            // Save note to database
            Note note = new Note(title, body, isFavourite);
            noteViewModel.insert(note);

            // Show confirmation message
            Toast.makeText(getContext(), "Note saved successfully!", Toast.LENGTH_SHORT).show();

            // Navigate back to AllNotesFragment
            Navigation.findNavController(view).navigateUp();
        });
    }
}
