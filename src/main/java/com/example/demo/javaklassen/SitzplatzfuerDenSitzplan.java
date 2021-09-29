package com.example.demo.javaklassen;

public class SitzplatzfuerDenSitzplan {
    private Sitzplatz sitzplatz;
    private Zustaende zustand;

    public SitzplatzfuerDenSitzplan(Sitzplatz sitzplatz, Zustaende zustand) {
        this.sitzplatz = sitzplatz;
        this.zustand = zustand;
    }

    public Sitzplatz getSitzplatz() {
        return sitzplatz;
    }

    public void setSitzplatz(Sitzplatz sitzplatz) {
        this.sitzplatz = sitzplatz;
    }

    public Zustaende getZustand() {
        return zustand;
    }

    public void setZustand(Zustaende zustand) {
        this.zustand = zustand;
    }
}
