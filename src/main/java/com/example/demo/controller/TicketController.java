package com.example.demo.controller;

import com.example.demo.entity.Tickets;
import com.example.demo.repository.TicketRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value ="/tickets", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void kundenAnlegen(@RequestBody Tickets ticket) {
        System.out.println("Es wurde ein Ticket angelegt!");
        ticketRepository.save(ticket);
    }

}

