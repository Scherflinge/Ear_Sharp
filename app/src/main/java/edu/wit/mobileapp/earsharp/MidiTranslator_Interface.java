package edu.wit.mobileapp.earsharp;

public interface MidiTranslator_Interface {
    void playChord(Chord chord);
    void playNote(Note_Enum note);
    void stopPlaying();
}
