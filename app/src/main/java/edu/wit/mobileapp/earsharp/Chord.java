package edu.wit.mobileapp.earsharp;

import android.provider.ContactsContract;

import java.util.List;

public class Chord {
    Note_Enum root;
    List<Note_Enum> extensions;

    public Chord(List<Note_Enum> incomingNotes){
        if(incomingNotes.size()>0){
            root = incomingNotes.remove(0);
        }
        if(incomingNotes.size()>0){
            extensions = incomingNotes;
        }
    }

    public Chord(Note_Enum incomingRoot, List<Note_Enum> incomingExtensions){
        root = incomingRoot;
        extensions = incomingExtensions;
    }
}
