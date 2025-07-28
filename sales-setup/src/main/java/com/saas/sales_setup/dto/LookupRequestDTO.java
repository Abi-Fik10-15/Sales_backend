package com.saas.sales_setup.dto;

import com.saas.sales_setup.model.LookupType;
import lombok.Data;

@Data
public class LookupRequestDTO {

    private LookupType type;

    private String value; // The text to display, e.g., "Public Organization"

    private String key; // The machine-readable key, e.g., "PUBLIC_ORG"

    private int sortOrder;
}