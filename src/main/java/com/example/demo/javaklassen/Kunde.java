package com.example.demo.javaklassen;

import java.util.ArrayList;

public class Kunde extends Person {

    private static int kundenID = 0;
    private final static boolean ADMINRECHTE = false;
    private String mobilfunknummer;
    private String email;
    private int rabattstufe;
    private boolean verifiziertesKonto;
    private boolean kundensperrung;
    private ArrayList<Ticket> alleTickets;

    public Kunde(String name, String vorname, int alter, String strasse, String hausnummer, int plz, String ort, String mobilfunknummer, String email) {
        super(name, vorname, alter, strasse, hausnummer, plz, ort);
        this.mobilfunknummer = mobilfunknummer;
        this.email = email;
        this.rabattstufe = 0;
        this.verifiziertesKonto = false;
        this.kundensperrung = false;
        this.alleTickets = new ArrayList<>();
    }

    public static int getKundenID() {
        return kundenID;
    }

    public static void setKundenID(int kundenID) {
        Kunde.kundenID = kundenID;
    }

    public String getMobilfunknummer() {
        return mobilfunknummer;
    }

    public void setMobilfunknummer(String mobilfunknummer) {
        this.mobilfunknummer = mobilfunknummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRabattstufe() {
        return rabattstufe;
    }

    public void setRabattstufe(int rabattstufe) {
        this.rabattstufe = rabattstufe;
    }

    public boolean isVerifiziertesKonto() {
        return verifiziertesKonto;
    }

    public void setVerifiziertesKonto(boolean verifiziertesKonto) {
        this.verifiziertesKonto = verifiziertesKonto;
    }

    public boolean isKundensperrung() {
        return kundensperrung;
    }

    public void setKundensperrung(boolean kundensperrung) {
        this.kundensperrung = kundensperrung;
    }

    public ArrayList<Ticket> getAlleTickets() {
        return alleTickets;
    }

    public void setAlleTickets(ArrayList<Ticket> alleTickets) {
        this.alleTickets = alleTickets;
    }
}
