package com.example.demo.controller;

import com.example.demo.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//TODO

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestdatenAnlegenTest {

    @Autowired
    private SitzplaetzeRepository sitzplaetzeRepository;

    @Autowired
    private KinoRepository kinoRepository;

    @Autowired
    private KinoSaalRepository kinoSaalRepository;

    @Autowired
    private SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository;

    @Autowired
    private VorstellungRepository vorstellungRepository;

    @Autowired
    private OMDBRepository omdbRepository;

    @Test
    void testdatenAnlegen() {
        TestdatenAnlegen controllerTestdaten = new TestdatenAnlegen(vorstellungRepository, sitzplaetzeRepository, sitzplaetzeFuerVorstellungRepository, kinoRepository, kinoSaalRepository, omdbRepository);
        Assertions.assertTrue(controllerTestdaten.testdatenAnlegen());
        Assertions.assertTrue(controllerTestdaten.sitzplanFuerSitzplaetzeAnlegen());
    }

    @Test
    void sitzplanFuerSitzplaetzeAnlegen() {
        TestdatenAnlegen controllerTestdaten = new TestdatenAnlegen(vorstellungRepository, sitzplaetzeRepository, sitzplaetzeFuerVorstellungRepository, kinoRepository, kinoSaalRepository, omdbRepository);
        Assertions.assertFalse(controllerTestdaten.sitzplanFuerSitzplaetzeAnlegen()); //Ohne die Vorstellungen zuvor anzulegen auch keine Sitzplätze für die Vorstellungen
        controllerTestdaten.testdatenAnlegen();
        Assertions.assertTrue(controllerTestdaten.sitzplanFuerSitzplaetzeAnlegen());
    }
}