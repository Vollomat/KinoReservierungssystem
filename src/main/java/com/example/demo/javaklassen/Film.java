package com.example.demo.javaklassen;

import java.util.Date;

public class Film {

    private static int zaehler = 0;
    private int filmID;
    private String titel;
    private String beschreibung;
    private Date erscheinungsdatum;
    private int langeInMinuten;

    public Film(String titel, String beschreibung, Date erscheinungsdatum, int langeInMinuten) {
        this.filmID = zaehler + 1;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.erscheinungsdatum = erscheinungsdatum;
        this.langeInMinuten = langeInMinuten;
        zaehler++;
    }

    public static int getZaehler() {
        return zaehler;
    }

    public static void setZaehler(int zaehler) {
        Film.zaehler = zaehler;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getErscheinungsdatum() {
        return erscheinungsdatum;
    }

    public void setErscheinungsdatum(Date erscheinungsdatum) {
        this.erscheinungsdatum = erscheinungsdatum;
    }

    public int getLangeInMinuten() {
        return langeInMinuten;
    }

    public void setLangeInMinuten(int langeInMinuten) {
        this.langeInMinuten = langeInMinuten;
    }
}
