package com.application.repository;

import com.application.Entity.Commission;
import com.application.Entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {


	@Query(value = "SELECT c FROM Commission c WHERE c.fromCurrency = :fromCurrency AND c.toCurrency = :toCurrency")
	Commission findCommissionByCurrency(@Param("fromCurrency") Currency fromCurrency,
										@Param("toCurrency") Currency toCurrency);

	@Query(value = "SELECT c.commissionPct FROM Commission c WHERE c.fromCurrency = :fromCurrency AND c.toCurrency = :toCurrency")
	BigDecimal findCommissionAmountByCurrency(@Param("fromCurrency") Currency fromCurrency, @Param("toCurrency") Currency toCurrency);
}
