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


    private static Message nachrichtVorbereiten(Session session, String myAccount, String empfaenger, String nachricht) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccount));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
        message.setSubject("Ihre Buchung bei MovieMaxx");
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText(nachricht);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(nachricht, "text/html");
        return message;
    }

    public static boolean emailversand(String emailadresse, String nachricht) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "mail.gmx.net");
        properties.put("mail.smtp.port", "587");
        String myAccount = "MovieMaxx@gmx.de";
        String myPassword = "#kino123487";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, myPassword);
            }
        });
        try {
            Message message = nachrichtVorbereiten(session, myAccount, emailadresse, nachricht);
            Transport.send(message);
            System.out.println("E-Mail erfolgreich versendet! An: " + emailadresse);
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

}
