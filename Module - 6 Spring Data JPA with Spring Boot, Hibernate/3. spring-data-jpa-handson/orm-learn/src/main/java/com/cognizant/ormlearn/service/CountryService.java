package com.cognizant.ormlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

/**
 * Service for Country operations.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> findCountriesByPartialName(String keyword) {
        LOGGER.info("Start findCountriesByPartialName keyword={}", keyword);
        return countryRepository.findByNameContaining(keyword);
    }

    @Transactional
    public List<Country> findCountriesByPartialNameSorted(String keyword) {
        LOGGER.info("Start findCountriesByPartialNameSorted keyword={}", keyword);
        return countryRepository.findByNameContainingOrderByNameAsc(keyword);
    }

    @Transactional
    public List<Country> findCountriesByAlphabet(String prefix) {
        LOGGER.info("Start findCountriesByAlphabet prefix={}", prefix);
        return countryRepository.findByNameStartingWith(prefix);
    }
}
