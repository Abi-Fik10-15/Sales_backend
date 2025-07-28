package com.saas.sales_setup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal; // Import BigDecimal

/**
 * Represents a single Tax Type lookup value.
 * This class is mapped to the "tax_types" collection in MongoDB.
 */
@Document(collection = "tax_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxType {

    @Id
    private String id;

    private String name;

    /**
     * The tax rate stored as a BigDecimal for financial accuracy.
     * For example, 15% should be stored as 0.15.
     */
    private BigDecimal rate; // Changed from double to BigDecimal

    private String tenantId;
    private boolean isInclusive;

}