package com.example.demo.controller;

import com.example.demo.entity.Tickets;
import com.example.demo.entity.Vorstellungen;
import com.example.demo.repository.TicketRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tickets")
@RestController
public class TicketController {

    private TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Tickets> alleTickets() {
        System.out.println("GET wurde ausgeführt für alle Tickets!");
        return ticketRepository.findAll();
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/tickets", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void kundenAnlegen(@RequestBody Tickets ticket) {
        if(existiertTicketSchon(ticket)) {
            System.err.println("Das Ticket existiert schon!");
        } else {
            System.out.println("Es wurde ein Ticket angelegt!");
            ticketRepository.save(ticket);
        }
    }


    public boolean existiertTicketSchon(Tickets ticket) {
        boolean ergebnis = false;

        ArrayList<Tickets> alleTickets = (ArrayList<Tickets>) ticketRepository.findAll();
        for (int i = 0; i < alleTickets.size(); i++) {
            if (alleTickets.get(i).getVornameDesBesitzers().equals(ticket.getVornameDesBesitzers()) && alleTickets.get(i).getNachnameDesBesitzers().equals(ticket.getNachnameDesBesitzers()) && alleTickets.get(i).getStartuhrzeit().equals(ticket.getStartuhrzeit())) {
                ergebnis = true;
                if(!alleTickets.get(i).getStatus().equals(ticket.getStatus())) {   //Update Status vom Ticket
                    ergebnis = false;
                    ticketRepository.delete(alleTickets.get(i));
                    ticketRepository.save(ticket);
                }
            }
        }

        return ergebnis;
    }

}

