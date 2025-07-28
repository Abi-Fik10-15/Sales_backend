package com.saas.sales_setup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Holds the results of a tax calculation.
 */
@Data
@AllArgsConstructor
public class TaxCalculationResultDTO {
    private BigDecimal basePrice;
    private BigDecimal taxAmount;
    private BigDecimal totalPrice;
}