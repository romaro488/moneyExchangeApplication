package com.application.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commission {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "FROM_CURRENCY")
    @NotNull(message = "Please provide fromCurrency")
    private Currency fromCurrency;

    @Column(name = "TO_CURRENCY")
    @NotNull(message = "Please provide toCurrency")
    private Currency toCurrency;

    @Column(name = "COMMISSION_PERCENT")
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    @NotNull(message = "Please provide a commission percent")
    private BigDecimal commissionPct;

}
