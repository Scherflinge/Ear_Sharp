package edu.wit.mobileapp.earsharp.game;

import edu.wit.mobileapp.earsharp.music.Interval;
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public interface GameInterface {
    void playChord();
    Note_Enum getKey();
    int getCurrentRound();
    int getMaxRounds();
    int getNumCorrect();
    void startNewRound();
    boolean checkInterval(Interval interval);
}
