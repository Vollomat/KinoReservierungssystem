package com.example.demo.javaklassen;

public class Sitzplatz {

    private static int zaehler = 0;
    private int sitzplatzID;
    private KinoSaal kinoSaal;
    private int reihe;
    private int platz;
    private int preisstufe;

    public Sitzplatz(KinoSaal kinoSaal, int reihe, int platz, int preisstufe) {
        this.sitzplatzID = zaehler + 1;
        this.kinoSaal = kinoSaal;
        this.reihe = reihe;
        this.platz = platz;
        this.preisstufe = preisstufe;
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

    public KinoSaal getKinoSaal() {
        return kinoSaal;
    }

    public void setKinoSaal(KinoSaal kinoSaal) {
        this.kinoSaal = kinoSaal;
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

    public int getPreisstufe() {
        return preisstufe;
    }

    public void setPreisstufe(int preisstufe) {
        this.preisstufe = preisstufe;
    }
}
