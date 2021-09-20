package com.example.demo;


import com.example.demo.javaklassen.*;
import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TreeSet;

class TicketTest {

    private Ticket ticket1;
    private TreeSet<Sitzplatz> alleSitzplaetzeDesKinosaals;

    private static final float STANDARDPREIS = 30.0F;

    @BeforeEach
    void setUp() {
        Kunde kunde1 = new Kunde("Mustermann", "Max", 20, "Teststrasse", "12", 55555, "Testort", "000100010000", "max.mustermann@gmx.de");
        Date datum = new Date();
        Film film1 = new Film("Terminator", "Maschinen", datum, 175);
        alleSitzplaetzeDesKinosaals = new TreeSet<>();
        KinoSaal kinoSaal1 = new KinoSaal(alleSitzplaetzeDesKinosaals);
        Vorstellung vorstellung1 = new Vorstellung(film1, kinoSaal1, datum, false);
        ticket1 = new Ticket(kunde1, vorstellung1, datum, (Sitzplatz) null, STANDARDPREIS);
    }

    @Nested
    class EqualTestTicket {
        @Test
        void preisBerechnenTest() {
            float benoetigterTicketpreis = 17.8F;
            float berechneterTicketpreis;
            ticket1.getBesitzer().setVerifiziertesKonto(true);
            ticket1.getBesitzer().setRabattstufe(2);
            ticket1.preisberechnen();
            berechneterTicketpreis = ticket1.getPreis();
            Assertions.assertEquals(benoetigterTicketpreis, berechneterTicketpreis);
        }

    }


}