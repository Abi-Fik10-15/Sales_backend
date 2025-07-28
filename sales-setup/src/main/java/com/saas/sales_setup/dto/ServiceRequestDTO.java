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
 * DTO for creating or updating a Service.
 * It contains all the information a client needs to provide, with validation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDTO {

    @NotBlank(message = "Service name cannot be blank")
    private String serviceName;

    @NotBlank(message = "Service category cannot be blank")
    private String serviceCategory;

    @NotBlank(message = "UOM cannot be blank")
    private String uom; // Unit of Measure

    @NotBlank(message = "Tax type cannot be blank")
    private String taxType;

    @NotEmpty(message = "At least one price detail is required")
    @Valid // Ensures validation is cascaded to the nested DTOs
    private List<ServicePriceDetailDTO> servicePriceDetails;
}