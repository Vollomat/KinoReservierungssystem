package com.example.demo.controller;

import com.example.demo.repository.OMDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OMDBControllerTest {

    @Autowired
    public OMDBRepository omdbRepository;

    @Test
    void filmeEinfuegen() throws IOException {
        OMDBController omdbController = new OMDBController(omdbRepository);

        Assertions.assertTrue(omdbController.filmeEinfuegen());

    }
}