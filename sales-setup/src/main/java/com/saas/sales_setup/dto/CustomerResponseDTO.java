package com.saas.sales_setup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for sending customer data back to the client.
 * Represents the complete customer resource, including the server-generated ID.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    private String id; // The unique ID generated by MongoDB
    private String tenantId; // The tenant this customer belongs to
    private String customerName;
    private String tinNo;
    private String vatNo;
    private String customerType;
    private String tradeName;
    private LocalDate tradeLicenseIssuedDate;
    private String businessType;
    private String tradeBusinessLicenseNo;
    private String contactPerson;
    private LocalDate registrationDate;

    // Nested DTOs for response
    private AddressDTO customerAddress;
    private AddressDTO tinLicenseAddress;
    private AddressDTO businessAddress;
    private List<AdditionalInfoDTO> additionalInfo;
}