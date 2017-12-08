package com.example.demo.web.controllers;


import com.example.demo.web.exceptions.DataFormatException;
import com.example.demo.backend.domain.Country;
import com.example.demo.backend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller довідника Країни
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/countries")
public class CountryController extends AbstractRestHandler {
    /** The application logger */
 //   private static final Logger LOG = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryService countryService;

    /**
     * /api/countries/
     * GET списку країн
     * @param request
     * @param response
     * @return list of countries(long id, String name)
     */
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Country> getAllCountry(HttpServletRequest request, HttpServletResponse response) {
        List<Country> list = new ArrayList<>();
        Iterable<Country> countries = this.countryService.getCountryAll();

        countries.forEach(list::add);
        return list;
    }

    /**
     * /api/countries/{id}
     * GET однієї країни
     * @param id
     * @param request
     * @param response
     * @return country(long id, String name)
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Country getCountry(
                 @PathVariable("id") Long id,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        Country country = this.countryService.getCountryByOne(id);
        return country;
    }

    /**
     * /api/countries/
     * POST вставка нового запису
     * @param country
     * @param request
     * @param response
     * @return country(long id, String name)
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public Country createCountry(@RequestBody Country country,
                           HttpServletRequest request, HttpServletResponse response) {
        Country countryNew = new Country(country.getName());
        this.countryService.saveAndFlushCountry(countryNew);
        return countryNew;
    }

    /**
     * /api/countries/{id}
     * PUT зміна запису
     * @param id
     * @param country
     * @param request
     * @param response
     * @return country(long id, String name)
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
   public Country updateCountry(
                           @PathVariable("id") Long id, @RequestBody Country country,
                                 HttpServletRequest request, HttpServletResponse response) {
        Country countryEdit = this.countryService.getCountryByOne(country.getId());
        checkResourceFound(countryEdit);
        if (id != country.getId()) {
            throw new DataFormatException("ID doesn't match!");
        }
        else {
            countryEdit.setName(country.getName());
            this.countryService.saveAndFlushCountry(countryEdit);
            return countryEdit;
        }

        }

    /**
     * /api/countries/{id}
     * DELETE видалення запису
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(
                           @PathVariable("id") Long id, HttpServletRequest request,
                              HttpServletResponse response) {
        checkResourceFound(this.countryService.getCountryByOne(id));
        this.countryService.deleteCountry(id);
    }


}
