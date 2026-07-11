package com.cognizant.ormlearn;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");
        
        countryService = context.getBean(CountryService.class);

        // 1. Hands-on 1: Retrieve all countries
        testGetAllCountries();

        // 2. Hands-on 5: Find country by code
        testFindCountryByCode("IN");
        testFindCountryByCode("US");

        // 3. Hands-on 5: Add a new country
        testAddCountry();

        // 4. Hands-on 5: Update the country name
        testUpdateCountry();

        // 5. Hands-on 5: Find countries by partial name matching
        testFindCountriesByPartialName();

        // 6. Hands-on 5: Delete country
        testDeleteCountry();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start - testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End - testGetAllCountries");
    }

    private static void testFindCountryByCode(String code) {
        LOGGER.info("Start - testFindCountryByCode for '{}'", code);
        try {
            Country country = countryService.findCountryByCode(code);
            LOGGER.debug("country={}", country);
        } catch (Exception e) {
            LOGGER.error("Country search failed: ", e);
        }
        LOGGER.info("End - testFindCountryByCode");
    }

    private static void testAddCountry() {
        LOGGER.info("Start - testAddCountry");
        Country country = new Country("ZZ", "Test Country");
        countryService.addCountry(country);
        try {
            Country retrieved = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Added Country={}", retrieved);
        } catch (Exception e) {
            LOGGER.error("Verify added country failed: ", e);
        }
        LOGGER.info("End - testAddCountry");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start - testUpdateCountry");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country");
            Country retrieved = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Updated Country={}", retrieved);
        } catch (Exception e) {
            LOGGER.error("Update country failed: ", e);
        }
        LOGGER.info("End - testUpdateCountry");
    }

    private static void testFindCountriesByPartialName() {
        LOGGER.info("Start - testFindCountriesByPartialName for 'ou'");
        List<Country> countries = countryService.findCountriesByPartialName("ou");
        LOGGER.debug("Countries containing 'ou': {}", countries);
        LOGGER.info("End - testFindCountriesByPartialName");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start - testDeleteCountry");
        countryService.deleteCountry("ZZ");
        try {
            Country retrieved = countryService.findCountryByCode("ZZ");
            LOGGER.error("Delete failed! Country still exists: {}", retrieved);
        } catch (Exception e) {
            LOGGER.info("Delete verified successfully (exception expected: {})", e.getMessage());
        }
        LOGGER.info("End - testDeleteCountry");
    }
}
