package com.example.demo.controller;

import com.example.demo.entity.Film;
import com.example.demo.repository.FilmRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    private FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping("")
    public List<Film> index() {
        System.out.println("GET wurde ausgeführt für alle Filme");
        return filmRepository.findAll();
    }

}

