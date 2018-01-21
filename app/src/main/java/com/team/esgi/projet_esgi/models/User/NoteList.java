package com.team.esgi.projet_esgi.models.User;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoteList {

    @SerializedName("note")
    @Expose
    private ArrayList<Note> notes = null;

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NoteList{" +
                "notes=" + notes +
                '}';
    }
}