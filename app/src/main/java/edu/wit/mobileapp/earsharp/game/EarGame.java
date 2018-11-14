package edu.wit.mobileapp.earsharp.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import android.os.Handler;

import edu.wit.mobileapp.earsharp.music.MidiTranslator;
import edu.wit.mobileapp.earsharp.music.Chord;
import edu.wit.mobileapp.earsharp.music.Extension;
import edu.wit.mobileapp.earsharp.music.Interval;
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public class EarGame implements GameInterface {
    private final Note_Enum allNotes[] = {Note_Enum.A1, Note_Enum.A$1, Note_Enum.B1, Note_Enum.C1, Note_Enum.C$1, Note_Enum.D1, Note_Enum.D$1, Note_Enum.E1, Note_Enum.F1, Note_Enum.F$1, Note_Enum.G1, Note_Enum.G$1, Note_Enum.A2, Note_Enum.A$2, Note_Enum.B2, Note_Enum.C2, Note_Enum.C$2, Note_Enum.D2, Note_Enum.D$2, Note_Enum.E2, Note_Enum.F2, Note_Enum.F$2, Note_Enum.G2, Note_Enum.G$2};
    private final int majorRoots[] = {0,2,4,5,7,9,11,12};
    private final Extension majorExtensions[] = {Extension.Maj, Extension.Min, Extension.Min, Extension.Maj, Extension.Maj, Extension.Min, Extension.Dim, Extension.Maj};
    private List<Chord> chordList;
    private Random random;
    private PlayNoteListener display;
    private Difficulty difficulty;

    private MidiTranslator midiPlayer;

    private Queue<Chord> recentlyGuessed;

    private Chord chordToGuess;

    private int currentRound = 0;
    private int maxRounds = 0;
    private int numCorrect = 0;
    private Note_Enum key;
    private Extension keyExtension;
    private boolean playRootFirst;

    private Handler handler = new Handler();


    @Override
    public void playChord(){
        playChord(chordToGuess);
    }

    List<Integer> highlightFirst, highlightSecond;
    Chord playFirst, playSecond;

    public void playChord(Chord newChord) {
        //Highlight notes
        highlightFirst = new ArrayList<>();
        highlightFirst.add(0);

        highlightSecond = new ArrayList<>();
        for(int i  = 1; i < 8; i++){
            highlightSecond.add(i);
        }

        playFirst = new Chord(key, keyExtension);
        playSecond = newChord;

        if(!playRootFirst){
            List<Integer> tempList = highlightFirst;
            highlightFirst = highlightSecond;
            highlightSecond = tempList;

            Chord tempChord = playFirst;
            playFirst = playSecond;
            playSecond = tempChord;
        }

        display.highlightNotes(highlightFirst);
        midiPlayer.playChord(playFirst);

        //stop Highlighting Notes
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                display.unhighlightNotes();
                midiPlayer.stopPlaying();
            }
        }, 250);

        //Highlight notes
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                display.highlightNotes(highlightSecond);
                midiPlayer.playChord(playSecond);
            }
        }, 250);

        //stop Highlighting Notes
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                display.unhighlightNotes();
                midiPlayer.stopPlaying();
            }
        }, 500);
        display.donePlayingNotes();
    }

    @Override
    public Note_Enum getKey() {
        return key;
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public int getMaxRounds() {
        return maxRounds;
    }

    @Override
    public int getNumCorrect() {
        return numCorrect;
    }

    @Override
    public void startNewRound() {
        if (key == null){
            currentRound = 0;
        }
        else{
            currentRound++;
        }
        key = allNotes[random.nextInt(12)];
        playRootFirst = random.nextBoolean();
        chordToGuess = newRandomChord();
        currentState = GameStates.Started;
    }

    @Override
    public boolean checkInterval(Interval interval) {

        int addition = interval.ordinal();
        int guess = key.ordinal()+addition;
        if(chordToGuess.getRoot().ordinal() == guess|| chordToGuess.getRoot().ordinal()%12 == guess%12){
            return true;
        }
        return false;
    }

    private enum GameStates{Started, NeedToPlayChord, ChordPlayed, ReplayChord, Finished}
    private GameStates currentState;

    public EarGame(PlayNoteListener playNoteListener, List<Chord> lessons, Difficulty difficulty){
        display = playNoteListener;
        chordList = lessons;
        currentState = GameStates.Started;
        this.difficulty = difficulty;
        random = new Random();
        recentlyGuessed = new ArrayDeque<>(chordList.size()>3? 3: chordList.size());
        handler = new Handler();
        if(difficulty == Difficulty.Easy){
            keyExtension = Extension.Maj;
        }
        midiPlayer = new MidiTranslator();
    }

    private Chord newRandomChord(){
        int newRand = random.nextInt(chordList.size());
        while (recentlyGuessed.contains(chordList.get(newRand))){
            newRand = random.nextInt(chordList.size());
        }
        Chord newChord = chordList.get(newRand);

        // We're going to assume everything is in A major

        recentlyGuessed.remove();
        recentlyGuessed.add(newChord);

        Note_Enum newNote = transposeNote(Note_Enum.A1, key, newChord.getRoot());

        return new Chord(newNote, newChord.getExtensions());
    }

    public Note_Enum transposeNote(Note_Enum previousKey, Note_Enum desiredKey, Note_Enum keyToChange){
        int newNote = previousKey.ordinal()+desiredKey.ordinal()+keyToChange.ordinal();
        newNote = newNote%12;
        return allNotes[newNote];
    }
}
