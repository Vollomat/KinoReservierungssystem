package com.example.demo.javaklassen;

import java.util.Date;
import java.util.TreeSet;

public class Vorstellung {

    private static int zaehler = 0;
    private int vorstellungsID;
    private Film film;
    private KinoSaal kinoSaal;
    private Date startDatum;
    private boolean dreiD;
    private TreeSet<SitzplatzfuerDenSitzplan> aktuelleSitzplaetze;

    public Vorstellung(Film film, KinoSaal kinoSaal, Date startDatum, boolean dreiD) {
        this.vorstellungsID = zaehler + 1;
        this.film = film;
        this.kinoSaal = kinoSaal;
        this.startDatum = startDatum;
        this.dreiD = dreiD;
        this.aktuelleSitzplaetze = null;
        zaehler++;
    }

    public static int getZaehler() {
        return zaehler;
    }

    public static void setZaehler(int zaehler) {
        Vorstellung.zaehler = zaehler;
    }

    public int getVorstellungsID() {
        return vorstellungsID;
    }

    public void setVorstellungsID(int vorstellungsID) {
        this.vorstellungsID = vorstellungsID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public KinoSaal getKinoSaal() {
        return kinoSaal;
    }

    public void setKinoSaal(KinoSaal kinoSaal) {
        this.kinoSaal = kinoSaal;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public boolean isDreiD() {
        return dreiD;
    }

    public void setDreiD(boolean dreiD) {
        this.dreiD = dreiD;
    }

    public TreeSet<SitzplatzfuerDenSitzplan> getAktuelleSitzplaetze() {
        return aktuelleSitzplaetze;
    }

    public void setAktuelleSitzplaetze(TreeSet<SitzplatzfuerDenSitzplan> aktuelleSitzplaetze) {
        this.aktuelleSitzplaetze = aktuelleSitzplaetze;
    }
}
