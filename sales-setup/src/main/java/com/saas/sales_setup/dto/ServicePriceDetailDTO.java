package com.saas.sales_setup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal; // Import BigDecimal
import java.time.LocalDate;

/**
 * DTO for Service Price Details. Can be used for both requests and responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicePriceDetailDTO {

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Service price cannot be null")
    @Positive(message = "Service price must be a positive number")
    private BigDecimal servicePrice; // Changed from Double to BigDecimal

    @NotBlank(message = "Reference letter number cannot be blank")
    private String referenceLetterNo;

    private String status; // e.g., "Active", "Inactive"
}