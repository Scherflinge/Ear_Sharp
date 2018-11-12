package edu.wit.mobileapp.earsharp;

import java.util.ArrayList;
import java.util.List;

public class EarGame {
    private List<Chord> chordList;
    private enum GameStates{Started, NeedToPlayChord, ChordPlayed, ReplayChord, Finished}
    private GameStates currentState;
    public EarGame(ArrayList<Chord> incomingChords){
        chordList = incomingChords;
        currentState = GameStates.Started;
    }

}
