package com.example.demo.javaklassen;

import java.util.Date;

public class Ticket {

    private static int zaehler = 0;
    private int ticketID;
    private Kunde besitzer;
    private Vorstellung vorstellung;
    private Date date;
    private boolean bezahlt;
    private Sitzplatz sitzplatz;
    private SitzplatzfuerDenSitzplan sitzplatzfuerDenSitzplan;
    private float preis;

    public Ticket(Kunde besitzer, Vorstellung vorstellung, Date date, Sitzplatz sitzplatz, float preis) {
        this.ticketID = zaehler + 1;
        this.besitzer = besitzer;
        this.vorstellung = vorstellung;
        this.date = date;
        this.sitzplatz = sitzplatz;
        this.preis = preis;
        this.bezahlt = false;
        zaehler++;
    }

    public Ticket(Kunde besitzer, Vorstellung vorstellung, Date date, SitzplatzfuerDenSitzplan sitzplatzfuerDenSitzplan, float preis) {
        this.ticketID = zaehler + 1;
        this.besitzer = besitzer;
        this.vorstellung = vorstellung;
        this.date = date;
        this.sitzplatzfuerDenSitzplan = sitzplatzfuerDenSitzplan;
        this.preis = preis;
        this.bezahlt = false;
        zaehler++;
    }

    public Object pdfTicketErstellen() {
        //TODO
        return null;
    }

    public Object rechnungErstellen() {
        //TODO
        return null;
    }

    public SitzplatzfuerDenSitzplan getSitzplatzfuerDenSitzplan() {
        return sitzplatzfuerDenSitzplan;
    }

    public void setSitzplatzfuerDenSitzplan(SitzplatzfuerDenSitzplan sitzplatzfuerDenSitzplan) {
        this.sitzplatzfuerDenSitzplan = sitzplatzfuerDenSitzplan;
    }

    public void versenden() {
        //TODO
    }

    public void preisberechnen() {
        int prozentualerRabatt = this.getBesitzer().getRabattstufe() * 10;
        if(!this.getBesitzer().isVerifiziertesKonto()) {
            prozentualerRabatt = prozentualerRabatt + 5;
        }
        float neuerPreis = preis * (1 - prozentualerRabatt);
        this.setPreis(neuerPreis);
    }

    public static int getZaehler() {
        return zaehler;
    }

    public static void setZaehler(int zaehler) {
        Ticket.zaehler = zaehler;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public Kunde getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Kunde besitzer) {
        this.besitzer = besitzer;
    }

    public Vorstellung getVorstellung() {
        return vorstellung;
    }

    public void setVorstellung(Vorstellung vorstellung) {
        this.vorstellung = vorstellung;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(boolean bezahlt) {
        this.bezahlt = bezahlt;
    }

    public Sitzplatz getSitzplatz() {
        return sitzplatz;
    }

    public void setSitzplatz(Sitzplatz sitzplatz) {
        this.sitzplatz = sitzplatz;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }
}
