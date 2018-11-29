package edu.wit.mobileapp.earsharp.music;

import java.util.ArrayList;
import java.util.List;

import edu.wit.mobileapp.earsharp.music.Extension;
import edu.wit.mobileapp.earsharp.music.Interval;

public class IntervalChord {
    public Interval root;
    public List<Extension> extension;
    public IntervalChord(Interval newRoot, Extension... newExtension){
        root = newRoot;
        extension = new ArrayList<>();
        for(int i = 0; i<newExtension.length;i++){
            extension.add(newExtension[i]);
        }
    }
    @Override
    public String toString() {
        String interval = root.toString();
        for(int i = 0;i<extension.size();i++) {
            Extension currentExtension = extension.get(i);
            switch (currentExtension) {
                case Min:
                    interval = interval.toLowerCase();
                    break;
                case Dim:
                    interval = interval.toLowerCase();
                    interval = interval + "°";
                    break;
                case Seven:
                    interval = interval + "7";
                    break;
                default:
                    break;
            }
        }
        return interval;
    }
}
