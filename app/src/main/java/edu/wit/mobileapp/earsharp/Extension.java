package edu.wit.mobileapp.earsharp;

import java.util.List;

public enum Extension {
    Maj("Maj", 4, 7), Min("Min", 3, 7), Dim("Dim",3, 6), Seven("7", 4, 7, 10), None("");
    public int[] notes;
    String name;
    Extension(String name, int... args){
        notes = args;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
