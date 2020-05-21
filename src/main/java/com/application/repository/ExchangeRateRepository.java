package com.application.repository;

import com.application.Entity.Currency;
import com.application.Entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

	@Query(value = "SELECT er FROM ExchangeRate er WHERE er.currencyFrom = :currencyFrom AND er.currencyTo = :currencyTo")
	ExchangeRate findExchangeRateByCurrency(@Param("currencyFrom") Currency currencyFrom,
											@Param("currencyTo") Currency currencyTo);

	@Query(value = "SELECT er.rate FROM ExchangeRate er WHERE er.currencyFrom = :currencyFrom AND er.currencyTo = :currencyTo")
	BigDecimal findRateAmountByCurrency(@Param("currencyFrom") Currency fromCurrency,
										@Param("currencyTo") Currency toCurrency);
}
