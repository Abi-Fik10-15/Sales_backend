package com.saas.sales_setup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for sending complete Service data to the client.
 * Includes server-generated fields like id and tenantId.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDTO {

    private String id;
    private String tenantId;
    private String serviceName;
    private String serviceCategory;
    private String uom;
    private String taxType;
    private List<ServicePriceDetailDTO> servicePriceDetails;
}