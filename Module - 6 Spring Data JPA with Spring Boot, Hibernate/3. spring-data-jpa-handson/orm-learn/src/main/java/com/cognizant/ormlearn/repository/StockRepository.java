package com.cognizant.ormlearn.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Stock;

/**
 * Repository for Stock entity.
 * Derived query methods from Hands-on 2 (previous module).
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Scenario 1: Stocks by code and date range ordered by date ascending
    List<Stock> findByCodeAndDateBetweenOrderByDateAsc(String code, LocalDate start, LocalDate end);

    // Scenario 2: Stocks by code where close price is greater than given value
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal close);

    // Scenario 3: Top 3 dates with highest volume
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Scenario 4: Top 3 dates when a given stock was lowest
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
