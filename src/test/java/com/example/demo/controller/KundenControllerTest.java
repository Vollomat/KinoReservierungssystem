package com.example.demo.controller;

import com.example.demo.entity.Kunden;
import com.example.demo.repository.KundenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KundenControllerTest {

    @Autowired
    private KundenRepository kundenRepository;


    @Test
    @Rollback(value = false)
    void alleKunden() {
        KundenController kundenController = new KundenController(kundenRepository);

        kundenController.getKundenRepository().deleteAll();

        Assertions.assertEquals(0 , kundenController.alleKunden().size());

        Kunden kunde1 = new Kunden(1, "Max", "Mustermann", "max.mustermann@gmx.de", 22, "Teststrasse", "22a", "66666", "Testort", "testpasswort", false);
        kundenController.kundenAnlegen(kunde1);
        Assertions.assertEquals(1, kundenController.alleKunden().size());

        Kunden kunde2 = new Kunden(1, "Max", "Mustermann", "max.mustermann@gmx.de", 22, "Teststrasse", "22a", "66666", "Testort", "testpasswort", false);
        kundenController.kundenAnlegen(kunde2);
        Assertions.assertEquals(1, kundenController.alleKunden().size());  //Erhöht sich nicht, da es derselbe Kunde ist und dieser schon existiert!

        Kunden kunde3 = new Kunden(3, "Maxxa", "Mustermannada", "max.mustermannda@gmx.de", 22, "Teststrassadae", "22ada", "66666", "Testodart", "testpaadasswort", false);
        kundenController.kundenAnlegen(kunde3);
        Assertions.assertEquals(2, kundenController.alleKunden().size());  //Erhöht sich um eins

        kundenController.getKundenRepository().deleteAll();

    }

    @Test
    void kundenAnlegen() {
        Kunden kunde1 = new Kunden(1, "Max", "Mustermann", "max.mustermann@gmx.de", 22, "Teststrasse", "22a", "66666", "Testort", "testpasswort", false);
        KundenController kundenController = new KundenController(kundenRepository);
        Assertions.assertTrue(kundenController.kundenAnlegen(kunde1));
        Assertions.assertFalse(kundenController.kundenAnlegen(kunde1));  //False da der Kunde schon existiert!

        kundenController.getKundenRepository().deleteAll();
    }

    @Test
    void passwortVergessen() {
        KundenController kundenController = new KundenController(kundenRepository);
        Assertions.assertEquals(null, kundenController.passwortVergessen("max.mu@gmx.de")); //Kunde existiert nicht mit dieser E-Mail

        //Todo

        kundenController.getKundenRepository().deleteAll();
    }

    @Test
    void einloggen() {
    }
}