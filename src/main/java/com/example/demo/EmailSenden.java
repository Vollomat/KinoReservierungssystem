package com.example.demo;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSenden {

    private static Message nachrichtVorbereiten(Session session, String myAccount, String empfaenger) throws Exception {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(myAccount));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
        message.setSubject("Ihre Buchung bei Moviemaxx");

        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        // Textteil der E-Mail
        messageBodyPart.setText("Ihr Ticketbuchung war erfolgreich! Ticket-ID: 123456, Vorstellung: test, ...");
        String htmlBody = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "\n" +
                "    <title>Ihre Buchung war erfolgreich!</title>\n" +
                "\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    \n" +
                "    <h1><center>Ihre Buchung war erfolgreich!</center></h1><br/>\n" +
                "    Hallo Patrick Vollstedt,\n" +
                "    \n" +
                "   \n" +
                "  </body>\n" +
                "</html>";
        messageBodyPart.setContent(htmlBody, "text/html");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        return message;
    }

    public static boolean emailversand() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "kinoticketreservierungssystem@gmail.com";
        String myPassword = "kino1234";
        String empfaenger = "patrick.vollstedt@gmx.de";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, myPassword);
            }
        });

        try {
            Message message = nachrichtVorbereiten(session, myAccount, empfaenger);
            Transport.send(message);
            System.out.println("E-Mail erfolgreich versendet!");
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }



}
