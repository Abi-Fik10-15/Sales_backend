package com.saas.sales_setup.service;

import com.saas.sales_setup.dto.TaxCalculationResultDTO;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Service interface for handling tax-related calculations.
 */
public interface TaxService {

    /**
     * Calculates tax based on a given price and tax type ID.
     *
     * @param price The price of the item or service.
     * @param taxTypeId The database ID of the TaxType.
     * @return An Optional containing the TaxCalculationResult, or empty if the taxTypeId is invalid.
     */
    Optional<TaxCalculationResultDTO> calculate(BigDecimal price, String taxTypeId);
}