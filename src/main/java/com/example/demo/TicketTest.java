package com.example.demo;



import com.example.demo.controller.TicketController;
import com.example.demo.entity.Tickets;
import com.example.demo.entity.Vorstellungen;
import com.example.demo.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class TicketTest {

    public TicketRepository ticketRepository;
    public BestellungenRepository bestellungenRepository;
    public SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository;
    public VorstellungRepository vorstellungRepository;
    public KundenRepository kundenRepository;


    @BeforeEach
    void setUp() {
        TicketRepository ticketRepository;
    }

    @Nested
    class EqualTestTicket {
        @Test
        void preisBerechnenTest() throws IOException, InterruptedException {
            Vorstellungen vorstellung1 = new Vorstellungen();
            Tickets ticket1 = new Tickets(1, "20:00", 3, "Terminator", 45.00, 23, 5, 5, 1);
            TicketController ticketController = new TicketController(ticketRepository, bestellungenRepository, sitzplaetzeFuerVorstellungRepository, vorstellungRepository, kundenRepository);
            Assertions.assertEquals("i", "i");
        }


    }

}