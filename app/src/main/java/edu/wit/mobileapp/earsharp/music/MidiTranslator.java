package edu.wit.mobileapp.earsharp.music;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MidiTranslator implements MidiTranslator_Interface {
    MidiDriverTranslator mc;
    private int velocity = 127;
    List<Integer> playingNotes;
    private int offset = 57;

    public MidiTranslator(){
        playingNotes = new ArrayList<>();
        mc = new MidiDriverTranslator();
    }

    public void playChord(Chord chord) {
        List<Extension> extensions = chord.getExtensions();

        int root = chord.getRoot().ordinal() + offset;

        mc.noteOn(root, velocity);
        playingNotes.add(root);

        for(int i = 0; i< extensions.size(); i++) {
            for (int j = 0; j < extensions.get(i).notes.length; j++) {
                int toPlay = root + extensions.get(i).notes[j] + offset;
                mc.noteOn(toPlay, velocity);
                playingNotes.add(toPlay);
            }
        }
    }

    @Override
    public void playNote(Note_Enum note) {
        int toPlay = note.name.indexOf(note.name) + 20;
        mc.noteOn(toPlay, velocity);
        playingNotes.add(toPlay);
    }

    @Override
    public void stopPlaying() {
        for (Integer note: playingNotes) {
            mc.noteOff(note);
        }
    }


}
