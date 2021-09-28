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
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>MovieMaxx</title>\n" +
                "    <script src=\"https://kit.fontawesome.com/57d79c9300.js\" crossorigin=\"anonymous\"></script>\n" +
                "    <link rel=\"stylesheet\" href=\"/style.css\">\n" +
                "</head>\n" +
                "<body marginwidth=\"50\" marginheight=\"10\" topmargin=\"10\" leftmargin=\"50\">\n" +
                "\n" +
                "  <table width=100%>\n" +
                "    <tbody>\n" +
                "        <tr bgcolor=\"#581520\">\n" +
                "          <th></th>\n" +
                "          <td></td>\n" +
                "          <td><h2>Buchungsbest&auml;tigung</h2></td>\n" +
                "          <td><img src=\"https://cdn.glitch.com/d439d6d0-1bac-45dc-89dc-45037a7528cf%2FMail-Logo.png?v=1632767682319\" width=\"500px\" align=\"right\"></td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  <br>\n" +
                "  <p align=\"left\" marginwidth=\"50\" marginheight=\"100\" topmargin=\"100\" leftmargin=\"50\">\n" +
                "    Vielen Dank f&uuml;r Ihre Buchung bei MovieMaxx!<br><br>\n" +
                "    \n" +
                "    Ihre Tickets finden Sie im Anhang wieder. Wir w&uuml;nschen Ihnen viel Spa&szlig; bei der Vorstellung im MovieMaxx Mannheim!<br><br>\n" +
                "    Bitte beachten Sie f&uuml;r den Besuch unseres Kinos die tagesaktuell g&uuml;ltigen Einlassregeln. <br>\n" +
                "    Welche Regeln aktuell gelten erfahren Sie auf der Webseite von <a href=\"/footarea/Covid.html\" style=\"color: #00BFBF\">MovieMaxx</a> oder der zust&auml;ndigen Beh&ouml;rdenwebseite.\n" +
                "    </p>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <h1>\n" +
                "      &Uuml;bersicht Ihrer Bestellung:\n" +
                "  </h1>\n" +
                "  <table>\n" +
                "    <tr>\n" +
                "      <td>Kino:</td>\n" +
                "      <td>MovieMaxx Mannheim, N7 17, 68159 Mannheim</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>Vorstellung:</td>\n" +
                "      <td>Vorstellung verlinken</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>Datum, Uhrzeit:</td>\n" +
                "      <td>verlinken</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>Sitzpl&auml;tze:</td>\n" +
                "      <td>verlinken</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>Sitzkategorie?:</td>\n" +
                "      <td>?</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>Bestellnummer:</td>\n" +
                "      <td>1234</td>\n" +
                "    </tr>\n" +
                "  \n" +
                "  </table>\n" +
                "\n" +
                "</body>\n" +
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
        String myPassword = "#kino1234873490z2jk3804j";
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

//############################################################################################################################################################

    private static Message nachrichtVorbereiten(Session session, String myAccount, String empfaenger, String nachricht) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccount));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
        message.setSubject("Ihr Passwort bei MovieMaxx");
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        // Textteil der E-Mail
        messageBodyPart.setText(nachricht);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        return message;
    }

    public static boolean emailversand(String emailadresse, String nachricht) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccount = "kinoticketreservierungssystem@gmail.com";
        String myPassword = "#kino1234873490z2jk3804j";
        String empfaenger = emailadresse;
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, myPassword);
            }
        });
        try {
            Message message = nachrichtVorbereiten(session, myAccount, empfaenger, nachricht);
            Transport.send(message);
            System.out.println("E-Mail erfolgreich versendet! An: " + emailadresse);
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

}
