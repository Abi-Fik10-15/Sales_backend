package com.saas.sales_setup.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for creating or updating a customer.
 * Contains all fields a client provides and includes validation rules.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "Customer Name is required")
    private String customerName;

    @NotBlank(message = "TIN Number is required")
    private String tinNo;

    @NotBlank(message = "VAT Number is required")
    private String vatNo;

    @NotBlank(message = "Customer Type is required")
    private String customerType;

    @NotBlank(message = "Trade Name is required")
    private String tradeName;

    @NotNull(message = "Trade License Issued Date is required")
    private LocalDate tradeLicenseIssuedDate;

    @NotBlank(message = "Business Type is required")
    private String businessType;

    @NotBlank(message = "Trade/Business License Number is required")
    private String tradeBusinessLicenseNo;

    private String contactPerson; // Optional

    @NotNull(message = "Registration Date is required")
    private LocalDate registrationDate;

    @Valid // Ensures nested validation for the AddressDTO
    private AddressDTO customerAddress;

    @Valid
    private AddressDTO tinLicenseAddress;

    @Valid
    private AddressDTO businessAddress;

    @Valid
    private List<AdditionalInfoDTO> additionalInfo;
}