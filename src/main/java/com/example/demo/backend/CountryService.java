package com.example.demo.backend;


import java.util.List;

public interface CountryService {
    Country getCountryByName(String cName);
    Country getCountryByOne(Long cId);
    List<Country> getCountryAll();
    void saveCountry(Country country);
    void saveAndFlushCountry(Country country);
    void deleteCountry(Long cId);
}
