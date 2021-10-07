package com.example.demo.controller;

import com.example.demo.EmailSenden;
import com.example.demo.entity.Kunden;
import com.example.demo.entity.Tickets;
import com.example.demo.repository.KundenRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tickets")
@RestController
public class TicketController {

    private TicketRepository ticketRepository;
    private KundenRepository kundenRepository;

    public TicketController(TicketRepository ticketRepository, KundenRepository kundenRepository) {
        this.ticketRepository = ticketRepository;
        this.kundenRepository = kundenRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Tickets> alleTickets() {
        System.out.println("GET wurde ausgeführt für alle Tickets!");
        return ticketRepository.findAll();
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/tickets", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Tickets ticketAnlegen(@RequestBody Tickets ticket) {
        if(existiertTicketSchon(ticket)) {
            System.err.println("Das Ticket existiert schon!");
            return null;
        } else {
            System.out.println("Es wurde ein Ticket angelegt!");
            ticketRepository.save(ticket);
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
                    "  </h1>\n" +
                    "  <table>\n" +
                    "    <tr>\n" +
                    "      <td>Kino:</td>\n" +
                    "      <td>MovieMaxx Mannheim, N7 17, 68159 Mannheim</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Film:</td>\n" +
                    "      <td>"+ ticket.getFilmName()+"</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Startuhrzeit:</td>\n" +
                    "      <td>"+ticket.getStartuhrzeit()+"</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Preis des Tickets exkl. MwSt.:</td>\n" +
                    "      <td>"+ ticket.getPreis()+"</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td>Ticketnummer:</td>\n" +
                    "      <td>" + ticket.getTicketid() + "</td>\n" +
                    "    </tr>\n" +
                    "  \n" +
                    "  </table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
            String emailTicketBesitzer = ticket.getEmail();
            if(emailTicketBesitzer != null) {
                EmailSenden.emailversand(emailTicketBesitzer, nachricht);
            } else {
                System.err.println("Es wurde keine E-Mail mitgegeben!");
            }
        }
        return ticket;
    }


    public boolean existiertTicketSchon(Tickets ticket) {
        boolean ergebnis = false;

        ArrayList<Tickets> alleTickets = (ArrayList<Tickets>) ticketRepository.findAll();
        for (Tickets alleTicket : alleTickets) {
            if (alleTicket.getEmail().equals(ticket.getEmail()) && alleTicket.getStartuhrzeit().equals(ticket.getStartuhrzeit()) && alleTicket.getFilmName().equals(ticket.getFilmName())) {
                ergebnis = true;
                if (!alleTicket.getStatus().equals(ticket.getStatus())) {   //Update Status vom Ticket
                    ergebnis = false;
                    ticketRepository.delete(alleTicket);
                    ticketRepository.save(ticket);
                }
            }
        }

        return ergebnis;
    }

}

