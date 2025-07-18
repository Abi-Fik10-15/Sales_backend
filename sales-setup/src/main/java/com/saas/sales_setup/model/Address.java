package com.saas.sales_setup.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Represents an embedded address object within the Customer document.
 * It is not a separate collection in MongoDB.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;

    private String houseNo;

    @NotBlank(message = "Mobile number is required")
    private String mobileNo;

    private String poBox;
}
