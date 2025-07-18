package com.saas.sales_setup.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a sales item price registration document in the MongoDB database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sales_items")
public class SalesItem {

    @Id
    private String id;

    @Field("tenant_id")
    private String tenantId;

    @NotBlank(message = "Item ID cannot be blank")
    @Field("item_id")
    private String itemId;

    @NotBlank(message = "Item Name cannot be blank")
    @Field("item_name")
    private String itemName;

    @Field("item_category")
    private String itemCategory;

    @Field("purchase_price")
    private Double purchasePrice;

    @Field("uom")
    private String uom; // Unit of Measure

    @NotEmpty(message = "At least one price detail is required")
    @Valid // Ensures nested validation
    @Field("price_details")
    private List<ItemPriceDetail> priceDetails;


    /**
     * Nested static class for item price details.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemPriceDetail {

        @NotNull(message = "Sales Unit Price cannot be null")
        @Positive(message = "Sales Unit Price must be a positive number")
        @Field("sales_unit_price")
        private Double salesUnitPrice;

        @NotNull(message = "Date cannot be null")
        @Field("date")
        private LocalDate date;

        @NotBlank(message = "Tax Type cannot be blank")
        @Field("tax_type")
        private String taxType;

        @Field("status")
        private String status;

        @Field("description")
        private String description;
    }
}
