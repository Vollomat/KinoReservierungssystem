package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

//TODO

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VorstellungControllerTest {

    @Test
    void alleVorstellungen() {
    }

    @Test
    void alleZVorstellungen() {
    }

    @Test
    void vorstellungAnlegen() {
    }

    @Test
    void sitzplaetzeDerVorstellung() {
    }

    @Test
    void sitzplanFuerVorstellungAnlegen() {
    }

    @Test
    void existiertVorstellungSchon() {
    }

    @Test
    void getSitzplaetzeRepository() {
    }

    @Test
    void getVorstellungRepository() {
    }

    @Test
    void getSitzplaetzeFuerVorstellungRepository() {
    }
}