package com.example.finalproject;

import androidx.cardview.widget.CardView;

import com.example.finalproject.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);

    void onLongClick(Notes notes, CardView cardView);
}
