package com.example.demo.javaklassen;

import java.util.TreeSet;

public class KinoSaal {

    private static int zaehler = 0;
    private int saalID;
    private TreeSet<Sitzplatz> sitzplaetze;

    public KinoSaal(TreeSet<Sitzplatz> sitzplaetze) {
        this.saalID = zaehler + 1;
        this.sitzplaetze = sitzplaetze;
        zaehler++;
    }

    public boolean sitzplatzHinzufuegen(Sitzplatz sitzplatz) {
        return this.sitzplaetze.add(sitzplatz);
    }

    public boolean sitzplatzLoeschen(Sitzplatz sitzplatz) {
        if(this.sitzplaetze.contains(sitzplatz)) {
            return this.sitzplaetze.remove(sitzplatz);
        }
        return false;
    }

    public static int getZaehler() {
        return zaehler;
    }

    public static void setZaehler(int zaehler) {
        KinoSaal.zaehler = zaehler;
    }

    public int getSaalID() {
        return saalID;
    }

    public void setSaalID(int saalID) {
        this.saalID = saalID;
    }

    public TreeSet<Sitzplatz> getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze(TreeSet<Sitzplatz> sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }
}
