package com.example.demo.controller;

import com.example.demo.entity.Bestellungen;
import com.example.demo.repository.BestellungenRepository;
import com.example.demo.repository.SitzplaetzeFuerVorstellungRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.VorstellungRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BestellungenControllerTest {

    @Autowired
    private BestellungenRepository bestellungenRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository;

    @Autowired
    private VorstellungRepository vorstellungRepository;

    @Test
    void bekommeAlleBestellungen() {
        BestellungenController bestellungenController = new BestellungenController(bestellungenRepository, ticketRepository, sitzplaetzeFuerVorstellungRepository, vorstellungRepository);
        Assertions.assertEquals(0, bestellungenController.bekommeAlleBestellungen().size());

        Bestellungen bestellungen = new Bestellungen(1, "test@gmx.de", "Kasse", -1);

        bestellungenController.bestellungAnlegen(bestellungen);

        Assertions.assertEquals(1, bestellungenController.bekommeAlleBestellungen().size());

        bestellungenController.bestellungAnlegen(bestellungen); //Die Bestellung existiert schon, daher darf sie nicht doppelt angelegt werden (bestellID Schlüssel)

        Assertions.assertEquals(1, bestellungenController.bekommeAlleBestellungen().size());

        bestellungenController.bestellungAnlegen(null);

        Assertions.assertEquals(1, bestellungenController.bekommeAlleBestellungen().size());

    }

}