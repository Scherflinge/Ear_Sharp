package edu.wit.mobileapp.earsharp;

public interface GameInterface {
    void playChord();
    Note_Enum getKey();
    int getCurrentRound();
    int getMaxRounds();
    int getNumCorrect();
    void startNewRound();
    boolean checkInterval(Interval interval);
}
