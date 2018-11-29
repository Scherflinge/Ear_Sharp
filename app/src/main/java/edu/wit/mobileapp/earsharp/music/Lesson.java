package edu.wit.mobileapp.earsharp.music;

import java.util.List;

public class Lesson {

    private int id;
    private String name;
    private String description;
    private List<IntervalChord> intervalChords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IntervalChord> getIntervalChords() {
        return intervalChords;
    }

    public void setIntervalChords(List<IntervalChord> intervalChords) {
        this.intervalChords = intervalChords;
    }
}
