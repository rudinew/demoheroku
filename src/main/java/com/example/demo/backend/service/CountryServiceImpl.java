package com.example.demo.backend.service;

import com.example.demo.backend.domain.Country;
import com.example.demo.backend.repositories.CountryRepository;
import com.example.demo.backend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public Country getCountryByName(String cName) {
        return countryRepository.findByName(cName);
    }

    @Override
    public Country getCountryByOne(Long cId) {
        return countryRepository.findOne(cId);
    }

    @Override
    public List<Country> getCountryAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    @Transactional
    public void saveAndFlushCountry(Country country) {
        countryRepository.saveAndFlush(country);
    }

    @Override
    @Transactional
    public void deleteCountry(Long cId) {
        countryRepository.delete(cId);
    }
}
