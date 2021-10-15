package com.example.demo;



import com.example.demo.entity.Tickets;
import com.example.demo.entity.Vorstellungen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class TicketTest {


    @BeforeEach
    void setUp() {

    }

    @Nested
    class EqualTestTicket {
        @Test
        void preisBerechnenTest() {
            Vorstellungen vorstellung1 = new Vorstellungen();
            Tickets ticket1 = new Tickets(1, "20:00", 3, "Terminator", 45.00, 23, 5, 5, 1);


        }


    }

}