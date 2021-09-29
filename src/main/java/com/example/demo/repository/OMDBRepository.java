package com.example.demo.repository;

import com.example.demo.entity.Kunden;
import com.example.demo.entity.OMDBFilme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OMDBRepository extends JpaRepository<OMDBFilme, Integer> {


}
