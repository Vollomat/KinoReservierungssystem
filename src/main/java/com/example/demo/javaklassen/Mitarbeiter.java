package com.example.demo.javaklassen;

public class Mitarbeiter {

    private static int zaehler = 0;
    private int mitarbeiterID;
    private final static boolean ADMINRECHTE = false;
    private String iban;
    private String bic;
    private String rentenVersicherungsnummer;
    private int steuerID;
    private int gehaltsstufe;

    public Mitarbeiter(String iban, String bic, String rentenVersicherungsnummer, int steuerID, int gehaltsstufe) {
        this.mitarbeiterID = zaehler + 1;
        this.iban = iban;
        this.bic = bic;
        this.rentenVersicherungsnummer = rentenVersicherungsnummer;
        this.steuerID = steuerID;
        this.gehaltsstufe = gehaltsstufe;
        zaehler++;
    }

    public Film filmAnlegen(Film film) {
        //TODO
        return null;
    }

    public Film filmLoeschen(Film film) {
        //TODO
        return null;
    }

    public Vorstellung vorstellungAnlegen(Vorstellung vorstellung) {
        //TODO
        return null;
    }

    public Vorstellung vorstellungLoeschen(Vorstellung vorstellung) {
        //TODO
        return null;
    }

    public KinoSaal kinoSaalAnlegen(KinoSaal kinoSaal) {
        //TODO
        return null;
    }

    public KinoSaal kinoSaalLoeschen(KinoSaal kinoSaal) {
        //TODO
        return null;
    }


    public static int getZaehler() {
        return zaehler;
    }

    public static void setZaehler(int zaehler) {
        Mitarbeiter.zaehler = zaehler;
    }

    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getRentenVersicherungsnummer() {
        return rentenVersicherungsnummer;
    }

    public void setRentenVersicherungsnummer(String rentenVersicherungsnummer) {
        this.rentenVersicherungsnummer = rentenVersicherungsnummer;
    }

    public int getSteuerID() {
        return steuerID;
    }

    public void setSteuerID(int steuerID) {
        this.steuerID = steuerID;
    }

    public int getGehaltsstufe() {
        return gehaltsstufe;
    }

    public void setGehaltsstufe(int gehaltsstufe) {
        this.gehaltsstufe = gehaltsstufe;
    }
}
