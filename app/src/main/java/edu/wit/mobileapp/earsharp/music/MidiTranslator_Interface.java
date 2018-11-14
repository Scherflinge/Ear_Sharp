package edu.wit.mobileapp.earsharp.music;

import edu.wit.mobileapp.earsharp.music.Chord;
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public interface MidiTranslator_Interface {
    void playChord(Chord chord);
    void playNote(Note_Enum note);
    void stopPlaying();
}
