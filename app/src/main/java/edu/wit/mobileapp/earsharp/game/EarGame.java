package edu.wit.mobileapp.earsharp.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import android.os.Handler;
import android.util.Log;

import edu.wit.mobileapp.earsharp.music.IntervalChord;
import edu.wit.mobileapp.earsharp.music.MidiTranslator;
import edu.wit.mobileapp.earsharp.music.Chord;
import edu.wit.mobileapp.earsharp.music.Extension;
import edu.wit.mobileapp.earsharp.music.Interval;
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public class EarGame implements GameInterface {
    private final Note_Enum allNotes[] = {Note_Enum.A1, Note_Enum.A$1, Note_Enum.B1, Note_Enum.C1, Note_Enum.C$1, Note_Enum.D1, Note_Enum.D$1, Note_Enum.E1, Note_Enum.F1, Note_Enum.F$1, Note_Enum.G1, Note_Enum.G$1, Note_Enum.A2, Note_Enum.A$2, Note_Enum.B2, Note_Enum.C2, Note_Enum.C$2, Note_Enum.D2, Note_Enum.D$2, Note_Enum.E2, Note_Enum.F2, Note_Enum.F$2, Note_Enum.G2, Note_Enum.G$2};
    private List<IntervalChord> chordList;
    private Random random;
    private PlayNoteListener display;
    private Difficulty difficulty;

    private MidiTranslator midiPlayer;

    private Queue<IntervalChord> recentlyGuessed;

    private Chord chordToGuess;

    private int currentRound = 0;
    public int maxRounds = 0;
    private int numCorrect = 0;
    private Note_Enum key;
    private Extension keyExtension;
    private boolean playRootFirst;
    private int durationToPlay = 1000;

    private Handler handler = new Handler();

    public EarGame(PlayNoteListener playNoteListener, List<IntervalChord> lessons, Difficulty difficulty){
        display = playNoteListener;
        chordList = lessons;
        currentState = GameStates.Started;
        this.difficulty = difficulty;
        random = new Random();
        recentlyGuessed = new ArrayDeque<>(chordList.size()>3? 3: chordList.size());
        if(difficulty == Difficulty.Easy){
            keyExtension = Extension.Maj;
        }
        else{
            keyExtension = Extension.Maj;
        }
        midiPlayer = new MidiTranslator();
    }

    @Override
    public void playChord(){
        if(chordToGuess ==  null){
            startNewRound();
        }
        playChord(chordToGuess);
    }
    List<Integer> highlightFirst, highlightSecond;

    Chord playFirst, playSecond;

    public void playChord(Chord newChord) {
        //Highlight notes
        highlightFirst = new ArrayList<>();


        highlightSecond = new ArrayList<>();
        for(int i  = 1; i < 7; i++){
            highlightSecond.add(i);
        }

        playFirst = new Chord(key, keyExtension);
        playSecond = newChord;

        if(playFirst.getRoot().ordinal() < playSecond.getRoot().ordinal()){
            highlightFirst.add(0);
        }
        else{
            highlightFirst.add(7);
        }

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

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                display.unhighlightNotes();
                midiPlayer.stopPlaying();

                display.highlightNotes(highlightSecond);
                midiPlayer.playChord(playSecond);
            }
        }, durationToPlay);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                display.unhighlightNotes();
                midiPlayer.stopPlaying();
                display.donePlayingNotes();
            }
        }, durationToPlay*2);
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
    public boolean checkInterval(IntervalChord intervalC) {
        Interval interval = intervalC.root;
        if(intervalC.extension != chordToGuess.getExtensions()){

        }
        int addition = interval.ordinal();
        int guess = key.ordinal()+addition;
        if(chordToGuess.getRoot().ordinal() == guess|| chordToGuess.getRoot().ordinal()%12 == guess%12){
            return true;
        }
        Log.v("EarGame", interval.toString() + "was inputted, looking for " + chordToGuess.toString());
        return false;
    }

    @Override
    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    private enum GameStates{Started, NeedToPlayChord, ChordPlayed, ReplayChord, Finished;}

    private GameStates currentState;

    private Chord newRandomChord(){
        int newRand = random.nextInt(chordList.size());
        while (recentlyGuessed.contains(chordList.get(newRand))){
            newRand = random.nextInt(chordList.size());
        }
        IntervalChord newChord = chordList.get(newRand);

        if(!recentlyGuessed.isEmpty()){
            recentlyGuessed.remove();
        }
        recentlyGuessed.add(newChord);

        Note_Enum newNote =  allNotes[newChord.root.ordinal()+key.ordinal()];

        return new Chord(newNote, newChord.extension);
    }

    public Note_Enum transposeNote(Note_Enum previousKey, Note_Enum desiredKey, Note_Enum keyToChange){
        int newNote = previousKey.ordinal()+desiredKey.ordinal()+keyToChange.ordinal();
        newNote = newNote%12;
        return allNotes[newNote];
    }
}
