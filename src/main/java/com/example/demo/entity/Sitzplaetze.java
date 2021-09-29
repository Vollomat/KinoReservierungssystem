package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sitzplaetze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sitzplatzID;

    private int reihe;

    private int spalte;

}
