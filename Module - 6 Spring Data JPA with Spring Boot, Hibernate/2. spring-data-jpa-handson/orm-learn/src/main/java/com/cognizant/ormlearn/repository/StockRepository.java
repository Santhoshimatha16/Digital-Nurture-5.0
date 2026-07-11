package com.cognizant.ormlearn.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Stock;

/**
 * Hands-on 2: StockRepository with Query Methods.
 *
 * Scenarios:
 * 1. FB stocks in September 2019
 * 2. GOOGL stocks where close > 1250
 * 3. Top 3 highest volume dates (all stocks)
 * 4. Bottom 3 NFLX close prices
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Scenario 1: FB stocks in a given month and year
    List<Stock> findByCodeAndDateBetweenOrderByDateAsc(String code, LocalDate startDate, LocalDate endDate);

    // Scenario 2: Stocks by code where close price > given value
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal closePrice);

    // Scenario 3: Top 3 highest volume records across all stocks
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Scenario 4: Bottom 3 NFLX close prices (lowest stocks)
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
