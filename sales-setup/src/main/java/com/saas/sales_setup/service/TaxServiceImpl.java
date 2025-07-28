package com.saas.sales_setup.service;

import com.saas.sales_setup.config.TenantContext;
import com.saas.sales_setup.dto.TaxCalculationResultDTO;
import com.saas.sales_setup.model.TaxType;
import com.saas.sales_setup.repository.TaxTypeRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * Implementation of the TaxService for handling all tax-related calculations.
 */
@Service
public class TaxServiceImpl implements TaxService {

    private final TaxTypeRepository taxTypeRepository;

    /**
     * Constructs the service with required dependencies injected by Spring.
     * @param taxTypeRepository The repository for accessing tax data.
     */
    public TaxServiceImpl(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    /**
     * Calculates tax based on a given price and tax type ID for the current tenant.
     *
     * @param price The price of the item or service. This can be tax-inclusive or exclusive.
     * @param taxTypeId The database ID of the TaxType.
     * @return An Optional containing the TaxCalculationResult, or an empty Optional if the taxTypeId is invalid for the tenant.
     */
    @Override
    public Optional<TaxCalculationResultDTO> calculate(BigDecimal price, String taxTypeId) {
        // 1. Get the current tenant's ID from the context.
        final String tenantId = TenantContext.getCurrentTenant();
        if (tenantId == null) {
            throw new IllegalStateException("Cannot perform calculation: Tenant ID is not available in the security context.");
        }

        // 2. Find the tax configuration, ensuring it belongs to the correct tenant.
        Optional<TaxType> taxTypeOpt = taxTypeRepository.findByIdAndTenantId(taxTypeId, tenantId);

        if (taxTypeOpt.isEmpty()) {
            return Optional.empty(); // Return empty if no valid tax type is found.
        }

        // 3. Perform the calculation using BigDecimal for financial accuracy.
        TaxType taxType = taxTypeOpt.get();
        BigDecimal rate = taxType.getRate();

        final int scale = 2; // Standard for most currencies (2 decimal places).
        final RoundingMode roundingMode = RoundingMode.HALF_UP; // Standard rounding rule.

        BigDecimal basePrice;
        BigDecimal taxAmount;
        BigDecimal totalPrice;

        if (taxType.isInclusive()) {
            // The provided price already includes tax. We need to work backwards.
            totalPrice = price;
            basePrice = totalPrice.divide(BigDecimal.ONE.add(rate), scale, roundingMode);
            taxAmount = totalPrice.subtract(basePrice);
        } else {
            // The provided price is the base. We need to calculate and add the tax.
            basePrice = price;
            taxAmount = basePrice.multiply(rate).setScale(scale, roundingMode);
            totalPrice = basePrice.add(taxAmount);
        }

        return Optional.of(new TaxCalculationResultDTO(basePrice, taxAmount, totalPrice));
    }
}