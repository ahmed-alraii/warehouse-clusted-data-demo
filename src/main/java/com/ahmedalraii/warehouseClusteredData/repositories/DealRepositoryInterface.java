package com.ahmedalraii.warehouseClusteredData.repositories;

import com.ahmedalraii.warehouseClusteredData.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DealRepositoryInterface extends JpaRepository<Deal, Long> {
    Deal findByAmountAndDealTimestampAndFromCurrencyAndToCurrency(double amount,
                                                                  String dealTimestamp,
                                                                  String fromCurrency,
                                                                  String toCurrency);
}
