package com.example.notesnavigation;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notesnavigation.db.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notesList = new ArrayList<>();


    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.body.setText(note.getBody());
        // Set favourite or not
        if (note.isFavorite() == true)
            holder.favStatus.setImageResource(R.drawable.ic_favourite);
        else
            holder.favStatus.setImageResource(R.drawable.ic_not_favourite);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    // ✅ Fix: Add a method to update the notes list
    public void setNotes(List<Note> notes) {
        this.notesList = notes;
        notifyDataSetChanged(); // Notify RecyclerView to update
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;
        ImageView favStatus;

        public NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TVNoteTitle);
            body = itemView.findViewById(R.id.TVNoteBody);
            favStatus = itemView.findViewById(R.id.IVFavStatus);
        }
    }
}

