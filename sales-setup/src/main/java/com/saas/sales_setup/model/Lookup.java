package com.saas.sales_setup.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lookups")
@CompoundIndex(name = "tenant_type_idx", def = "{'tenantId' : 1, 'type': 1}")
public class Lookup {

    @Id
    private String id;

    private String tenantId;

    private LookupType type;

    private String value; // e.g., "Private Limited Company"

    private String key; // e.g., "PLC"

    private int sortOrder;

    private boolean active = true;
}