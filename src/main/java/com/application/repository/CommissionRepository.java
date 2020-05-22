package com.application.repository;

import com.application.Entity.Commission;
import com.application.Entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

	Commission findCommissionByCurrency(Currency fromCurrency, Currency toCurrency);
}
