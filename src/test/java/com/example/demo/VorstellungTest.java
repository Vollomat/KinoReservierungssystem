package com.example.demo;


import com.example.demo.javaklassen.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

class TicketTest {

    private Ticket ticket1;
    private ArrayList<Sitzplatz> alleSitzplaetzeDesKinosaals;

    private static final float STANDARDPREIS = 30.0F;

    @BeforeEach
    void setUp() {
        Kunde kunde1 = new Kunde("Mustermann", "Max", 20, "Teststrasse", "12", 55555, "Testort", "000100010000", "max.mustermann@gmx.de");
        Date datum = new Date();
        Film film1 = new Film("Terminator", "Maschinen", datum, 175);
        alleSitzplaetzeDesKinosaals = new ArrayList<>();
        KinoSaal kinoSaal1 = new KinoSaal(alleSitzplaetzeDesKinosaals);
        Vorstellung vorstellung1 = new Vorstellung(film1, kinoSaal1, datum, false);
        ticket1 = new Ticket(kunde1, vorstellung1, datum, (Sitzplatz) null, STANDARDPREIS);
    }

    @Nested
    class EqualTestTicket {
        @Test
        void preisBerechnenTest() {
            int anzahlFreierSitzplaetze = 20;
            int anzahlBesetzteSitzplaetze = 1;
            //1 von 20 Sitzplätzen sind gebucht --> Gesamtauslastung 5% --> Rabatt siehe Dokumentation!
            float benoetigterTicketpreis = 17.8F;
            float berechneterTicketpreis;
            ArrayList<Sitzplatz> aktuellfreieSitzplaetze = new ArrayList<>();
            for(int i = 0; i < anzahlFreierSitzplaetze ; i++) {
                aktuellfreieSitzplaetze.add(new Sitzplatz(i,i));
            }
            ArrayList<SitzplatzfuerDenSitzplan> aktuellbesetzteSitzplaetze = new ArrayList<>();
            for(int j = 0; j < anzahlBesetzteSitzplaetze; j++) {
                aktuellbesetzteSitzplaetze.add(new SitzplatzfuerDenSitzplan(new Sitzplatz(j, j), Zustaende.GEBUCHT));
            }
            ticket1.getVorstellung().setFreieSitzplaetze(aktuellfreieSitzplaetze);
            ticket1.getVorstellung().setGebuchteSitzplaetze(aktuellbesetzteSitzplaetze);
            ticket1.getBesitzer().setVerifiziertesKonto(true);
            ticket1.getBesitzer().setRabattstufe(2);
            ticket1.preisberechnen();
            berechneterTicketpreis = ticket1.getPreis();
            Assertions.assertEquals(benoetigterTicketpreis, berechneterTicketpreis);
        }

    }


}