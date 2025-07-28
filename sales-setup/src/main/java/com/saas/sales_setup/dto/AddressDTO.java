package com.saas.sales_setup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for address information. Used within other DTOs.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    // Assuming standard address fields based on common usage
    @NotBlank(message = "Region cannot be blank")
    private String region;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private String subCity;
    private String woreda;
    private String houseNo;
}