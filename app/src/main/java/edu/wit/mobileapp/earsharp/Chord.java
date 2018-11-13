package edu.wit.mobileapp.earsharp;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class Chord {
    Note_Enum root;
    List<Extension> extensions;

    public Chord(Note_Enum incomingRoot, List<Extension> incomingExtensions){
        root = incomingRoot;
        extensions = incomingExtensions;
    }

    public Chord(Note_Enum incomingRoot, Extension incomingExtension){
        root = incomingRoot;
        extensions = new ArrayList<>();
        extensions.add(incomingExtension);
    }

    @Override
    public String toString() {
        String toReturn = root.toString();
        for (Extension e:extensions
             ) {
            toReturn = toReturn + e.toString();
        }
        return toReturn;
    }
}
