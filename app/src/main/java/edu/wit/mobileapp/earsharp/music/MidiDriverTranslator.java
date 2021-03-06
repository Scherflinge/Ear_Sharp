package edu.wit.mobileapp.earsharp.music;

import android.util.Log;

import org.billthefarmer.mididriver.MidiDriver;

import java.util.ArrayList;
import java.util.HashMap;

public class MidiDriverTranslator {
    private MidiDriver md = new MidiDriver();
    private byte event[];
    private final String TAG = "MidiDriver";
    public MidiDriverTranslator(){
        md.start();
    }

    void noteOn(int note, int velocity) {
        // Construct a note ON message for the middle C at maximum velocity on channel 1:
        Log.v(TAG, "playing note " + note);
        event = new byte[3];
        event[0] = (byte) (0x90 | 0x00);  // 0x90 = note On, 0x00 = channel 1
        event[1] = (byte) note;  // 0x3C = middle C
        event[2] = (byte) velocity;  // 0x7F = the maximum velocity (127)

        // Internally this just calls write() and can be considered obsoleted:
        //midiDriver.queueEvent(event);

        // Send the MIDI event to the synthesizer.
        md.write(event);
    }

    void noteOff(int note){
        // Construct a note OFF message for the middle C at minimum velocity on channel 1:
        Log.v(TAG, "attempting to turn off note "+note);

        event = new byte[3];
        event[0] = (byte) (0x80 | 0x00);  // 0x80 = note Off, 0x00 = channel 1
        event[1] = (byte) note;  // 0x3C = middle C
        event[2] = (byte) 0x00;  // 0x00 = the minimum velocity (0)

        // Send the MIDI event to the synthesizer.
        md.write(event);
    }
}
