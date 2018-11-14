package edu.wit.mobileapp.earsharp.game;

import java.util.List;

public interface PlayNoteListener {
    void highlightNotes(List<Integer> intervalList);
    void unhighlightNotes();
    void donePlayingNotes();
}
