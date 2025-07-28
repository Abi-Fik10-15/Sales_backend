package com.saas.sales_setup.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for creating or updating a Sales Item.
 * Includes client-provided fields and validation rules.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesItemRequestDTO {

    @NotBlank(message = "Item ID cannot be blank")
    private String itemId;

    @NotBlank(message = "Item Name cannot be blank")
    private String itemName;

    private String itemCategory;

    private Double purchasePrice;

    private String uom; // Unit of Measure

    @NotEmpty(message = "At least one price detail is required")
    @Valid // Cascade validation to the nested DTOs
    private List<ItemPriceDetailDTO> priceDetails;
}