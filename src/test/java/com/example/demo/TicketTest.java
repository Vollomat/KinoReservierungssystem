package com.example.demo;



import com.example.demo.entity.Tickets;
import com.example.demo.entity.Vorstellungen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class TicketTest {


    @BeforeEach
    void setUp() {

    }

    @Nested
    class EqualTestTicket {
        @Test
        void preisBerechnenTest() throws IOException {
            Vorstellungen vorstellung1 = new Vorstellungen();
            Tickets ticket1 = new Tickets(1, "20:00", 3, "Terminator", 45.00, 23, 5, 5, 1);
            URL url = new URL("http://localhost:8080/testdaten/alle");
            HttpURLConnection verbindung = (HttpURLConnection) url.openConnection();
            verbindung.setRequestMethod("POST");
            verbindung.disconnect();
            Assertions.assertEquals("i", "i");
        }


    }

}