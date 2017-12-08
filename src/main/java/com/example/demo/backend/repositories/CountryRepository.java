package com.example.demo.backend.repositories;

import com.example.demo.backend.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String cName);

    List<Country> findAll();

}
