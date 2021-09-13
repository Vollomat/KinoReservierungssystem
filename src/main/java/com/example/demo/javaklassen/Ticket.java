package com.example.demo.javaklassen;

import java.util.Date;

public class Ticket {

    private static int ticketID;
    private Kunde besitzer;
    private Vorstellung vorstellung;
    private Date date;
    private boolean bezahlt;
    private Sitzplatz sitzplatz;
    private float preis;

    public Ticket(Kunde besitzer, Vorstellung vorstellung, Date date, Sitzplatz sitzplatz, float preis) {
        this.besitzer = besitzer;
        this.vorstellung = vorstellung;
        this.date = date;
        this.sitzplatz = sitzplatz;
        this.preis = preis;
        this.bezahlt = false;
    }

    public static int getTicketID() {
        return ticketID;
    }

    public static void setTicketID(int ticketID) {
        Ticket.ticketID = ticketID;
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
