package edu.wit.mobileapp.earsharp;

public enum Note_Enum {
    A1("A"), A$1("Bb"), B1("B"), C1("C"), C$1("Db"), D1("D"), D$1("Eb"), E1("E"), F1("F"), F$1("Gb"), G1("G"), G$1("Ab"), A2("A"), A$2("Bb"), B2("B"), C2("C"), C$2("Db"), D2("D"), D$2("Eb"), E2("E"), F2("F"), F$2("Gb"), G2("G"), G$2("Ab");

    String name;
    private Note_Enum(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
