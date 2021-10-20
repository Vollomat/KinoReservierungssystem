package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailSendenTest {

    @Test
    public void emailsenden(){

        String emailMitRichtigemFormat = "patrick.vollstedt@gmx.de";
        String emailMitFalschemFormat = " idjaddadwjrw@";
        String nachricht = "Test-Email";
        Assertions.assertFalse(EmailSenden.emailversand(emailMitFalschemFormat, nachricht));
        Assertions.assertTrue(EmailSenden.emailversand(emailMitRichtigemFormat, nachricht));
    }

}