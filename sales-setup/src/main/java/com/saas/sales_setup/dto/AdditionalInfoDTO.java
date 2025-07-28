package com.saas.sales_setup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the key-value additional information pairs.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInfoDTO {
    @NotBlank(message = "Additional Information label cannot be blank")
    private String label;

    @NotBlank(message = "Additional Information value cannot be blank")
    private String value;
}