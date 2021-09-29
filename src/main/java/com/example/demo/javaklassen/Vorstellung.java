package com.example.demo.javaklassen;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class Vorstellung {

    private static int zaehler = 0;
    private int vorstellungsID;
    private Film film;
    private KinoSaal kinoSaal;
    private Date startDatum;
    private boolean dreiD;
    private ArrayList<Sitzplatz> freieSitzplaetze; //alle belegten Sitzplätze
    private ArrayList<SitzplatzfuerDenSitzplan> gebuchteSitzplaetze;
    private static final float STANDARDPREIS = 30.0F;

    public Vorstellung(Film film, KinoSaal kinoSaal, Date startDatum, boolean dreiD) {
        this.vorstellungsID = zaehler + 1;
        this.film = film;
        this.kinoSaal = kinoSaal;
        this.startDatum = startDatum;
        this.dreiD = dreiD;
        this.freieSitzplaetze = kinoSaal.getSitzplaetze();
        this.gebuchteSitzplaetze = new ArrayList<>();
        zaehler++;
    }

    public Ticket[] ticketBuchen(Kunde besitzer, ArrayList<Sitzplatz> sitzplaetze) {
        Ticket[] tickets = new Ticket[sitzplaetze.size()];
        Date date = new Date();
        for(int i = 0; i < sitzplaetze.size(); i++) {
            if (this.freieSitzplaetze.contains(sitzplaetze.get(i))) {
                tickets[i] = new Ticket(besitzer, this, date, sitzplaetze.get(i), STANDARDPREIS);
                tickets[i].preisberechnen();
                //TreeSets erneuern
            } else {
                System.out.println("Der angegebene Sitzplatz ist schon vergeben!!!!!");
            }
        }
        return tickets;
    }

    public Ticket[] ticketBuchen(Kunde besitzer, int anzahl) {
        Ticket[] tickets = new Ticket[anzahl];
        Date date = new Date();
        for(int i = 0; i < anzahl; i++) {
            if(this.getFreieSitzplaetze().size() > 0) {
                tickets[i] = new Ticket(besitzer, this, date, this.getFreieSitzplaetze().get(0), STANDARDPREIS);
                tickets[i].preisberechnen();
                //TreeSets erneuern
            }
        }
        return tickets;
    }

    public boolean ticketStornieren(Ticket ticket) {
        boolean stornieren = false;
        if(ticket.getSitzplatzfuerDenSitzplan() != null) {
            SitzplatzfuerDenSitzplan sitzplatzfuerDenSitzplan = ticket.getSitzplatzfuerDenSitzplan();
            this.freieSitzplaetze.add(sitzplatzfuerDenSitzplan.getSitzplatz());
            this.gebuchteSitzplaetze.remove(sitzplatzfuerDenSitzplan);
            stornieren = true;
        } else {
            if(ticket.getSitzplatz() != null) {
                Sitzplatz sitzplatz = ticket.getSitzplatz();
                SitzplatzfuerDenSitzplan sitzplatzfuerDenSitzplan = new SitzplatzfuerDenSitzplan(sitzplatz, Zustaende.PRAESENT);
                this.freieSitzplaetze.add(sitzplatzfuerDenSitzplan.getSitzplatz());
                this.gebuchteSitzplaetze.remove(sitzplatzfuerDenSitzplan);
                stornieren = true;
            }
        }
        return stornieren;
    }

    public ArrayList<Sitzplatz> getReservierteSitzplaetze() {
        ArrayList<Sitzplatz> alleReserviertenSitzplaetze = new ArrayList<>();
        Iterator<SitzplatzfuerDenSitzplan> iterator = this.getGebuchteSitzplaetze().iterator();
        for (int i = 0; i < this.getGebuchteSitzplaetze().size(); i++) {
            while (iterator.hasNext()) {
                if(iterator.next().getZustand().equals(Zustaende.RESERVIERT)) {
                    alleReserviertenSitzplaetze.add(iterator.next().getSitzplatz());
                }
            }
        }

        //TODO
        //Vielleicht Fehler drinnen!

        return alleReserviertenSitzplaetze;
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

    public ArrayList<Sitzplatz> getFreieSitzplaetze() {
        return freieSitzplaetze;
    }

    public void setFreieSitzplaetze(ArrayList<Sitzplatz> freieSitzplaetze) {
        this.freieSitzplaetze = freieSitzplaetze;
    }

    public ArrayList<SitzplatzfuerDenSitzplan> getGebuchteSitzplaetze() {
        return gebuchteSitzplaetze;
    }

    public void setGebuchteSitzplaetze(ArrayList<SitzplatzfuerDenSitzplan> gebuchteSitzplaetze) {
        this.gebuchteSitzplaetze = gebuchteSitzplaetze;
    }
}
