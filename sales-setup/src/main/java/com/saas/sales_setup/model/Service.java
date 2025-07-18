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
 * Represents a service document in the MongoDB database, styled like the Customer class.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "services")
public class Service {

    @Id
    private String id;

    @Field("tenant_id")
    private String tenantId;

    @NotBlank(message = "Service name cannot be blank")
    @Field("service_name")
    private String serviceName;

    @NotBlank(message = "Service category cannot be blank")
    @Field("service_category")
    private String serviceCategory;

    @NotBlank(message = "UOM cannot be blank")
    @Field("uom")
    private String uom; // Unit of Measure

    @NotBlank(message = "Tax type cannot be blank")
    @Field("tax_type")
    private String taxType;

    @NotEmpty(message = "At least one price detail is required")
    @Valid // This ensures that the elements inside the list are also validated
    @Field("service_price_details")
    private List<ServicePriceDetail> servicePriceDetails;


    /**
     * Nested static class for service price details.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServicePriceDetail {

        @NotNull(message = "Date cannot be null")
        @Field("date")
        private LocalDate date;

        @NotNull(message = "Service price cannot be null")
        @Positive(message = "Service price must be a positive number")
        @Field("service_price")
        private Double servicePrice;

        @NotBlank(message = "Reference letter number cannot be blank")
        @Field("reference_letter_no")
        private String referenceLetterNo;

        @Field("status")
        private String status; // e.g., "Active", "Inactive"
    }
}
