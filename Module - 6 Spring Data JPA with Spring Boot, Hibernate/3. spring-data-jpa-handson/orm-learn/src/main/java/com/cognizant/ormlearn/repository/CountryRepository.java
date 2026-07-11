package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

/**
 * Repository for Country entity.
 * Provides derived query methods used in Hands-on 1 (previous module).
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Partial name search (LIKE %keyword%)
    List<Country> findByNameContaining(String keyword);

    // Partial name search sorted ascending
    List<Country> findByNameContainingOrderByNameAsc(String keyword);

    // Alphabet index search (name starts with given letter)
    List<Country> findByNameStartingWith(String prefix);
}
