package com.application.repository;

import com.application.Entity.Currency;
import com.application.Entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {

	ExchangeRate findExchangeRateByCurrency(Currency currencyFrom, Currency currencyTo);
}
