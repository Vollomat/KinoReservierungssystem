package com.example.demo.javaklassen;

import java.util.Date;
import java.util.TreeSet;

public class Vorstellung {

    private static int vorstellungsID;
    private Film film;
    private KinoSaal kinoSaal;
    private Date startDatum;
    private boolean dreiD;
    private TreeSet<SitzplatzfuerDenSitzplan> aktuelleSitzplaetze;

    public Vorstellung(Film film, KinoSaal kinoSaal, Date startDatum, boolean dreiD) {
        this.film = film;
        this.kinoSaal = kinoSaal;
        this.startDatum = startDatum;
        this.dreiD = dreiD;
        this.aktuelleSitzplaetze = null;
    }

    public static int getVorstellungsID() {
        return vorstellungsID;
    }

    public static void setVorstellungsID(int vorstellungsID) {
        Vorstellung.vorstellungsID = vorstellungsID;
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
