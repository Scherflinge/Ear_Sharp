package edu.wit.mobileapp.earsharp;

public enum Interval {
    I("I"), bII("bII"), II("II"), bIII("bIII"), III("III"), IV("IV"), bV("bV"), V("V"), bVI("bVI"), VI("VI"), bVII("bVII"), VII("VII");
    private String name;
    Interval(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getOrdinal(){
        return this.ordinal();
    }
}
