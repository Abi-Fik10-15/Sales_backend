package com.saas.sales_setup.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPriceDetail {

    @NotNull(message = "Sales Unit Price cannot be null")
    @Positive(message = "Sales Unit Price must be a positive number")
    @Field("sales_unit_price")
    private BigDecimal salesUnitPrice; // Changed to BigDecimal

    @NotNull(message = "Date cannot be null")
    @Field("date")
    private LocalDate date;

    @NotBlank(message = "Tax Type ID cannot be blank")
    @Field("tax_type_id")
    private String taxTypeId; // Renamed for clarity

    @Field("status")
    private String status;

    @Field("description")
    private String description;
}