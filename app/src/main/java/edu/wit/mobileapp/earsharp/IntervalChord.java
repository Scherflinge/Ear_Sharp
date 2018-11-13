package edu.wit.mobileapp.earsharp;

public class IntervalChord {
    public Interval root;
    public Extension extension;
    public IntervalChord(Interval newRoot, Extension newExtension){
        root = newRoot;
        extension = newExtension;
    }

    public String toString() {
        String interval = root.toString();

        switch(extension){
            case Dim:
                interval = interval.toLowerCase();
                interval = interval + "°";
                break;
            case Seven:
                interval = interval.toLowerCase();
                interval = interval+"7";
                break;
            default:
                break;
        }
        return interval;
    }
}
