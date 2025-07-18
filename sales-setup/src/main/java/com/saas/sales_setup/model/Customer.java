package com.saas.sales_setup.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
 * Represents a customer document in the MongoDB database.
 * The @Document annotation marks this class as a MongoDB document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    @Field("tenant_id")
    private String tenantId;

    @NotBlank(message = "Customer Name is required")
    @Field("customer_name")
    private String customerName;

    @NotBlank(message = "TIN Number is required")
    @Field("tin_no")
    private String tinNo;

    @NotBlank(message = "VAT Number is required")
    @Field("vat_no")
    private String vatNo;

    @NotBlank(message = "Customer Type is required")
    @Field("customer_type")
    private String customerType;

    @NotBlank(message = "Trade Name is required")
    @Field("trade_name")
    private String tradeName;

    @NotNull(message = "Trade License Issued Date is required")
    @Field("trade_license_issued_date")
    private LocalDate tradeLicenseIssuedDate;

    @NotBlank(message = "Business Type is required")
    @Field("business_type")
    private String businessType;

    @NotBlank(message = "Trade/Business License Number is required")
    @Field("trade_business_license_no")
    private String tradeBusinessLicenseNo;

    @Field("contact_person")
    private String contactPerson; // Optional

    @NotNull(message = "Registration Date is required")
    @Field("registration_date")
    private LocalDate registrationDate;

    // --- Addresses corresponding to the UI tabs ---

    @Valid // Ensures nested validation
    @Field("customer_address")
    private Address customerAddress;

    @Valid
    @Field("tin_license_address")
    private Address tinLicenseAddress;

    @Valid
    @Field("business_address")
    private Address businessAddress;

    @Field("additional_info")
    private List<AdditionalInfo> additionalInfo;

    /**
     * Nested static class for key-value pairs.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdditionalInfo {
        @NotBlank(message = "Additional Information label cannot be blank")
        private String label;

        @NotBlank(message = "Additional Information value cannot be blank")
        private String value;
    }
}