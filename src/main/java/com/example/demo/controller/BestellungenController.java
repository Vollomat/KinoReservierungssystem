package com.example.demo.controller;

import com.example.demo.EmailSenden;
import com.example.demo.entity.Bestellungen;
import com.example.demo.entity.Tickets;
import com.example.demo.repository.BestellungenRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/bestellungen")
@RestController
public class BestellungenController {

    private BestellungenRepository bestellungenRepository;
    private TicketRepository ticketRepository;

    public BestellungenController(BestellungenRepository bestellungenRepository, TicketRepository ticketRepository) {
        this.bestellungenRepository = bestellungenRepository;
        this.ticketRepository = ticketRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bestellungen> bekommeAlleBestellungen() {
        return bestellungenRepository.findAll();
    }


    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int bestellungAnlegen(@RequestBody Bestellungen bestellungen) {
        ArrayList<Bestellungen> alleBestellungen = (ArrayList<Bestellungen>) bestellungenRepository.findAll();
        for (Bestellungen value : alleBestellungen) {
            if (bestellungen.getBestellID() == value.getBestellID()) {
                System.err.println("Die Bestellung existiert schon mit dieser ID!");
                return 9999;
            }
        }
        bestellungenRepository.save(bestellungen);
        return bestellungen.getBestellID();
    }

    @RequestMapping(value = "/emailsenden",produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/emailsenden", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean emailSenden(@RequestBody int bestellid) {
        String emailBestellungBesitzer = null;
        String nachricht = "<!DOCTYPE html>\n" +
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
                "          <td><img src=\"https://cdn.glitch.com/d439d6d0-1bac-45dc-89dc-45037a7528cf%2FMail-Logo.png?v=1632767682319\" width=\"200px\" align=\"right\"></td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  <br>\n" +
                "  <p align=\"left\" marginwidth=\"50\" marginheight=\"100\" topmargin=\"100\" leftmargin=\"50\">\n" +
                "    Vielen Dank f&uuml;r Ihre Buchung bei MovieMaxx!<br><br>\n" +
                "    \n" +
                "    Wir w&uuml;nschen Ihnen viel Spa&szlig; bei der Vorstellung im MovieMaxx Mannheim!<br><br>\n" +
                "    Bitte beachten Sie f&uuml;r den Besuch unseres Kinos die tagesaktuell g&uuml;ltigen Einlassregeln. <br>\n" +
                "    Welche Regeln aktuell gelten erfahren Sie auf der Webseite von <a href=\"/footarea/Covid.html\" style=\"color: #00BFBF\">MovieMaxx</a> oder der zust&auml;ndigen Beh&ouml;rdenwebseite.\n" +
                "    </p>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <h1>\n" +
                "      &Uuml;bersicht Ihrer Bestellung:\n" +
                "  </h1>\n";
        ArrayList<Bestellungen> alleBestellungen = (ArrayList<Bestellungen>) bestellungenRepository.findAll();
        ArrayList<Tickets> alleTickets = (ArrayList<Tickets>) ticketRepository.findAll();
        ArrayList<Tickets> ticketsDerBestellung = new ArrayList<>();
        for (Tickets alleTicket : alleTickets) {
            if (alleTicket.getBestellungID() == bestellid) {
                ticketsDerBestellung.add(alleTicket);
            }
        }
        for (Tickets tickets : ticketsDerBestellung) {
            nachricht = nachricht + "  <table>\n" +
                    "    <tr>\n" +
                    "      <td>Kino:</td>\n" +
                    "      <td>MovieMaxx Mannheim, N7 17, 68159 Mannheim</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Film:</td>\n" +
                    "      <td>" + tickets.getFilmName() + "</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Startuhrzeit:</td>\n" +
                    "      <td>" + tickets.getStartuhrzeit() + "</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Preis des Tickets exkl. MwSt.:</td>\n" +
                    "      <td>" + tickets.getPreis() + "</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Ticketnummer:</td>\n" +
                    "      <td>" + tickets.getTicketid() + "</td>\n" +
                    "    </tr>\n" +
                    "  \n" +
                    "  </table>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "    <br>\n";
        }
        nachricht = nachricht + "\n" +
                "</body>\n" +
                "</html>";
        for (Bestellungen bestellungen : alleBestellungen) {
            if (bestellungen.getBestellID() == bestellid) {
                emailBestellungBesitzer = bestellungen.getEmail();
            }
        }
        if(emailBestellungBesitzer != null) {
            EmailSenden.emailversand(emailBestellungBesitzer, nachricht);
            return true;
        } else {
            System.err.println("Es wurde keine passende Bestellung gefunden!");
            return false;
        }

    }

}
