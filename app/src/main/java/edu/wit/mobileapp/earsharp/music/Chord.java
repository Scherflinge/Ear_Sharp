package edu.wit.mobileapp.earsharp.music;

import java.util.ArrayList;
import java.util.List;

public class Chord {

    private Note_Enum root;
    private List<Extension> extensions;

    public Chord(Note_Enum incomingRoot, List<Extension> incomingExtensions){
        root = incomingRoot;
        extensions = incomingExtensions;
    }

    public Chord(Note_Enum incomingRoot, Extension incomingExtension){
        root = incomingRoot;
        extensions = new ArrayList<>();
        extensions.add(incomingExtension);
    }

    public Note_Enum getRoot() {
        return root;
    }

    public void setRoot(Note_Enum root) {
        this.root = root;
    }

    public List<Extension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
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
