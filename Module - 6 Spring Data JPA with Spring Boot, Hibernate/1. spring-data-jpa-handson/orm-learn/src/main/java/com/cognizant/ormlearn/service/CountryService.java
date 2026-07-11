package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String countryCode) throws Exception {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new Exception("Country not found");
        }
        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name) throws Exception {
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new Exception("Country not found");
        }
        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    @Transactional(readOnly = true)
    public List<Country> findCountriesByPartialName(String name) {
        return countryRepository.findByNameContaining(name);
    }

    // Part 2: Sorted ascending by name
    @Transactional(readOnly = true)
    public List<Country> findCountriesByPartialNameSorted(String name) {
        return countryRepository.findByNameContainingOrderByNameAsc(name);
    }

    // Part 3: Search by starting alphabet
    @Transactional(readOnly = true)
    public List<Country> findCountriesByAlphabet(String alphabet) {
        return countryRepository.findByNameStartingWith(alphabet);
    }
}
