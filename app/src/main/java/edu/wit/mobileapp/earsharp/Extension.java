package edu.wit.mobileapp.earsharp;

import java.util.List;

public enum Extension {
    Maj(4, 7), Min(3, 7), Dim(3, 6), Seven(4, 7, 10), None();
    public int[] notes;
    Extension(int... args){
        notes = args;
    }
}
