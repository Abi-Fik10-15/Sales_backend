package com.saas.sales_setup.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private BigDecimal purchasePrice; // Changed to BigDecimal

    @Field("uom")
    private String uom; // Unit of Measure

    @NotEmpty(message = "At least one price detail is required")
    @Valid // Ensures nested validation on the list of price details
    @Field("price_details")
    private List<ItemPriceDetail> priceDetails;

    // The nested ItemPriceDetail class has been removed from here.
}