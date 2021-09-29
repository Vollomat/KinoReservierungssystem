package com.example.demo.javaklassen;

public class Sitzplatz {

    private static int zaehler = 0;
    private int sitzplatzID;
    private int reihe;
    private int platz;

    public Sitzplatz(int reihe, int platz) {
        this.sitzplatzID = zaehler + 1;
        this.reihe = reihe;
        this.platz = platz;
        zaehler++;
    }

    public static int getZaehler() {
        return zaehler;
    }

    public static void setZaehler(int zaehler) {
        Sitzplatz.zaehler = zaehler;
    }

    public int getSitzplatzID() {
        return sitzplatzID;
    }

    public void setSitzplatzID(int sitzplatzID) {
        this.sitzplatzID = sitzplatzID;
    }

    public int getReihe() {
        return reihe;
    }

    public void setReihe(int reihe) {
        this.reihe = reihe;
    }

    public int getPlatz() {
        return platz;
    }

    public void setPlatz(int platz) {
        this.platz = platz;
    }

}
