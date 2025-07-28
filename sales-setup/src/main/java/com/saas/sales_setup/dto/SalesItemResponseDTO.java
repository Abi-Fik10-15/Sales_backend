package com.saas.sales_setup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for sending complete Sales Item data to the client.
 * Includes server-generated fields like id and tenantId.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesItemResponseDTO {

    private String id;
    private String tenantId;
    private String itemId;
    private String itemName;
    private String itemCategory;
    private Double purchasePrice;
    private String uom;
    private List<ItemPriceDetailDTO> priceDetails;
}