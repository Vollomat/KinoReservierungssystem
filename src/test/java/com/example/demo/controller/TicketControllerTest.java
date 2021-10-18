package com.example.demo.controller;

import com.example.demo.entity.Tickets;
import com.example.demo.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TicketControllerTest {

    @Autowired
    public TicketRepository ticketRepository;

    @Autowired
    public BestellungenRepository bestellungenRepository;

    @Autowired
    public SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository;

    @Autowired
    public VorstellungRepository vorstellungRepository;

    @Autowired
    public KundenRepository kundenRepository;


    @Test
    @Rollback(value = false)
    void alleTickets() throws Exception {
        TicketController ticketController = new TicketController(ticketRepository, bestellungenRepository, sitzplaetzeFuerVorstellungRepository, vorstellungRepository, kundenRepository);

        Assertions.assertEquals(0, ticketController.alleTickets().size()); //Erst sollte die DB leer sein

        Tickets ticket = new Tickets(1, "20:00", 3, "Terminator", 45.00, 23, 5, 5, 1);
        ticketController.getTicketRepository().save(ticket);
        Assertions.assertEquals(1, ticketController.alleTickets().size()); //Dann sollte die DB nur ticket enthalten --> size = 1

        Tickets ticket2 = new Tickets(2, "22:00", 4, "Terminator2", 5.00, 24, 2, 1, 2);
        ticketController.getTicketRepository().save(ticket2);
        Assertions.assertEquals(2, ticketController.alleTickets().size()); //Dann sollte die DB nur ticket und ticket2 enthalten --> size = 2
    }

    @Test
    void ticketAnlegen() {
        Tickets ticket = new Tickets(1, "20:00", 3, "Terminator", 45.00, 23, 5, 5, 1);

        TicketController ticketController = new TicketController(ticketRepository, bestellungenRepository, sitzplaetzeFuerVorstellungRepository, vorstellungRepository, kundenRepository);

        Assertions.assertNotNull(ticketController.ticketAnlegen(ticket));

        Assertions.assertEquals(-1, ticketController.ticketAnlegen(ticket));  //Da das Ticket schon exisitert wird als Rückgabe -1 zurückgegeben.

        Assertions.assertEquals(-1, ticketController.ticketAnlegen(null));  //Bei null sollte die Rückgabe -1 sein, da kein Ticket erzeugt wird und in der DB gespeichert wird.
    }

    @Test
    void kinosaalnummerBekommen() {
    }

    @Test
    void existiertTicketSchon() {
    }

    @Test
    void sitzplatzStatusAufReserviertSetzen() {
    }

    @Test
    void ticketPreisBerechnung() {
        TicketController ticketController = new TicketController(ticketRepository, bestellungenRepository, sitzplaetzeFuerVorstellungRepository, vorstellungRepository, kundenRepository);
        //Todo
    }
}