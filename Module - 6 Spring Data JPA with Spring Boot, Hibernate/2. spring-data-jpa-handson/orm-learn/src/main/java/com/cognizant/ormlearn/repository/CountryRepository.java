package com.cognizant.ormlearn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Country;

/**
 * Hands-on 1: CountryRepository with Query Methods.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Part 1: Search by partial name (any position)
    List<Country> findByNameContaining(String name);

    // Part 2: Partial name search sorted ascending by name
    List<Country> findByNameContainingOrderByNameAsc(String name);

    // Part 3: Search by starting alphabet
    List<Country> findByNameStartingWith(String alphabet);
}
