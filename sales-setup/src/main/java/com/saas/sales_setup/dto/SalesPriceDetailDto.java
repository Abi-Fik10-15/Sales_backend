package com.saas.sales_setup.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for responding with the full details of a sales price,
 * including the calculated tax.
 */
@Data
@Builder
public class SalesPriceDetailDto {
    private BigDecimal basePrice;
    private BigDecimal taxAmount;
    private BigDecimal totalPriceWithTax;
    private LocalDate effectiveDate;
}