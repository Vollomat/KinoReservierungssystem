package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BestellungenControllerTest {

    @Test
    void bekommeAlleBestellungen() {
    }

    @Test
    void bestellungAnlegen() {
    }

    @Test
    void emailSenden() {
    }

    @Test
    void preisBerechnenAllerTicketsDerBuchung() {
    }

    @Test
    void sitzplatzStatusAufGebuchtSetzen() {
    }
}