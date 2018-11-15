package edu.wit.mobileapp.earsharp.music;

import java.util.List;

import javax.sound.midi.*;

public class MidiTranslator implements MidiTranslator_Interface {
    static MidiChannel[] mc;
    private int velocity = 300;

    @Override
    public void playChord(Chord chord) {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        mc = synth.getChannels();
        Instrument[] inst = synth.getDefaultSoundbank().getInstruments();
        synth.loadInstrument(instr[0]);

        List<Extension> extensions = chord.getExtensions();

        int root = chord.getRoot().name.indexOf(chord.getRoot().toString()) + 20;

        mc[0].noteOn(root, velocity);
        mc[0].noteOn(root + Integer.parseInt(extensions.get(1).toString()), velocity);
        mc[0].noteOn(root + Integer.parseInt(extensions.get(2).toString()), velocity);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {}

        mc[0].noteOff(root);
        mc[0].noteOff(root + Integer.parseInt(extensions.get(1).toString()));
        mc[0].noteOff(root + Integer.parseInt(extensions.get(2).toString()));
    }

    @Override
    public void playNote(Note_Enum note) {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        mc = synth.getChannels();
        Instrument[] inst = synth.getDefaultSoundbank().getInstruments();
        synth.loadInstrument(instr[0]);

        mc[0].noteOn(note.name.indexOf(note.name) + 20, velocity);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {}

        mc[0].noteOff(note.name.indexOf(note.name) + 20);
    }

    @Override
    public void stopPlaying() {

    }


}
