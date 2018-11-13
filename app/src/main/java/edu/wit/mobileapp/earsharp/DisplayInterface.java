package edu.wit.mobileapp.earsharp;

import java.util.List;

public interface DisplayInterface {
    void highlightNotes(List<Integer> intervalList);
    void unhighlightNotes();
    void donePlayingNotes();
}
