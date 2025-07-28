package com.saas.sales_setup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Item Price Details.
 * Used for both request and response objects.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPriceDetailDTO {

    @NotNull(message = "Sales Unit Price cannot be null")
    @Positive(message = "Sales Unit Price must be a positive number")
    private Double salesUnitPrice;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotBlank(message = "Tax Type cannot be blank")
    private String taxType;

    private String status;

    private String description;
}